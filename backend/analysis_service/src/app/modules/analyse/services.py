
import pandas as pd

from io import StringIO
from typing import Dict

from app.modules.common import(
    db,
    files,
    run_in_executor
)

from app.config import init_config
from app.utils import get_config_type

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

    async def analyse_facility_query(self, file_id: str) -> Dict[str, str]:
        
        db = await db.factory(init_config(get_config_type()))
        files = await files.factory(init_config(get_config_type()))

        db_model = await db.find_by_id(file_id)
        file_content = await files.read_file(db_model['path'])

        df = await self.retrieve_dataframe(file_content)

        return {}
