
from arq.connections import create_pool, ArqRedis
import pandas as pd
import nengo_dl

from io import StringIO
from pathlib import Path
from typing import Dict, Union

from app.modules.common import(
    db,
    files,
    run_in_executor
)

from app.config import init_config
from app.utils import get_config_type, base_path
from app.settings.arq import settings
from app.modules.analyse.tasks import (
    get_models_path,
    get_data_and_labels,
    snn_model_template
)

FACILITY_STATUS = Dict[str, Union[str,Dict[str, str]]]

class DBService:
    """Service for work with DB instances"""

    async def check_for_file(self, file_id: str) -> bool:

        db = await db.factory(init_config(get_config_type()))
        files = await files.factory(init_config(get_config_type()))

        db_model = await db.find_by_id(file_id)

        if not db_model:
            return False

        return files.check_path(db_model['path'])

class AnalysisService:
    """Service for preparing the input data"""

    @run_in_executor
    def retrieve_dataframe(self, content: str) -> pd.DataFrame:
        return pd.read_table(
            StringIO(content), sep=';'
        )

    @run_in_executor
    def check_for_model_inner(self, name: str) -> bool:

        models_path = get_models_path()

        model_files = [
            elem.name
            for elem in models_path.iterdir()
            if elem.is_file()
        ]

        return name in model_files

    @run_in_executor
    def launch_predict(self, input_data: pd.DataFrame, file_name: str) -> list:

        data, _ = get_data_and_labels(input_data)

        x = input_data.shape[0]
        y = input_data.shape[1]

        net, out_probe = snn_model_template(x, y)
        file_path = get_models_path() / Path(f"{file_name}.params")

        sim = nengo_dl.Simulator(net)
        sim.load_params(str(file_path))
        predicted_data = sim.predict(data)
        sim.close()

        return predicted_data.to_list()

    async def launch_facility_analyse(self, file_id: str) -> FACILITY_STATUS:

        config_object = init_config(get_config_type())
        
        db = await db.factory(config_object)
        files = await files.factory(config_object)

        db_model: dict = await db.find_by_id(file_id)

        if not db_model:
            return {
                'status': 'not_found'
            }

        file_path: str = db_model['path']

        file_content = await files.read_file(file_path)

        temp_path = file_path[file_path.rfind('/'):len(file_path)]
        file_name = temp_path.split('_')[0]

        df = await self.retrieve_dataframe(file_content)

        redis_job_launch: ArqRedis = await create_pool(settings)

        await redis_job_launch.enqueue_job(
            'build_model',
            file_name,
            df
        )

        return {
            file_name: {
                'status': 'preparing'
            }
        }

    async def check_for_model(self, file_id: str) -> bool:

        config_object = init_config(get_config_type())
        db = await db.factory(config_object)

        db_model: dict = await db.find_by_id(file_id)

        if not db_model:
            return {
                'status': 'not_found'
            }

        file_path: str = db_model['path']

        temp_path = file_path[file_path.rfind('/'):len(file_path)]
        file_name = temp_path.split('_')[0]

        return await self.check_for_model_inner(file_name)

    async def launch_analyse(self, file_id: str) -> list:

        config_object = init_config(get_config_type())
        
        db = await db.factory(config_object)
        files = await files.factory(config_object)

        db_model: dict = await db.find_by_id(file_id)

        if not db_model:
            return {
                'status': 'not_found'
            }

        file_path: str = db_model['path']
        temp_path = file_path[file_path.rfind('/'):len(file_path)]
        file_name = temp_path.split('_')[0]

        file_content = await files.read_file(file_path)

        df = await self.retrieve_dataframe(file_content)

        return await self.launch_predict(df, file_name)

    async def transfer_models(self, show_status: bool) -> str:
        
        redis_job_launch: ArqRedis = await create_pool(settings)

        await redis_job_launch.enqueue_job(
            'upload_models'
        )

        if show_status:
            return 'job_launched'
