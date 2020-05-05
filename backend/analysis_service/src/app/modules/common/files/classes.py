
import contextlib
from abc import abstractmethod
from pathlib import Path

from pyhdfs import HdfsClient

from app.modules.common import AsyncObject, run_in_executor
from app.config.classes import DataSourceConfig

class FilesWork:
    """Abstract class for working with files"""

    @abstractmethod
    async def check_path(self, path: str) -> bool:
        raise NotImplementedError("Method is not implemented yet")

    @abstractmethod
    async def create_folder(self, path: str) -> bool:
        raise NotImplementedError("Method is not implemented yet")

    @abstractmethod
    async def create_file(self, path: str) -> None:
        raise NotImplementedError("Method is not implemented yet")

    @abstractmethod
    async def append_to_file(self, path: str, content: str) -> None:
        raise NotImplementedError("Method is not implemented yet")

    @abstractmethod
    async def read_file(self, path: str) -> str:
        raise NotImplementedError("Method is not implemented yet")

class HDFSFilesWork(FilesWork, AsyncObject):
    """Class for working with files in HDFS"""

    async def __init__(self, config: DataSourceConfig):
        super().__init__()
        self.hdfs_client = HdfsClient(
            hosts=config.hdfs_url
        )

    @run_in_executor
    def check_existence(self, path: str) -> bool:
        return self.hdfs_client.exists(path)

    async def check_path(self, path: str) -> bool:
        return await self.check_existence(path)

    @run_in_executor
    def create_dir(self, path: str) -> bool:
        return self.hdfs_client.mkdirs(path)

    async def create_folder(self, path: str) -> bool:
        return await self.create_dir(path)

    @run_in_executor
    def create_file_inner(self, path: str) -> None:
        self.hdfs_client.create(path, '')

    async def create_file(self, path: str) -> None:
        await self.create_file_inner(path)

    @run_in_executor
    def append_to_file_inner(self, path: str, content: str) -> None:
        self.hdfs_client.append(path, content)

    async def append_to_file(self, path: str, content: str) -> None:
        await self.append_to_file_inner(path, content)

    @run_in_executor
    def read_file_inner(self, path) -> str:
        with contextlib.closing(self.hdfs_client.open(path)) as f:
            return f.read()

    async def read_file(self, path: str) -> str:
        return await self.read_file_inner(path)

class MemoryFilesWork(FilesWork, AsyncObject):
    """Class for working with files in memory"""

    async def __init__(self, base_path: str):
        super().__init__()
        self.base_path = Path(base_path)

    @run_in_executor
    def check_existence(self, path: str) -> bool:

        final_path = self.base_path / Path(path)

        return final_path.exists()

    async def check_path(self, path: str) -> bool:
        return await self.check_existence(path)

    @run_in_executor
    def create_dir(self, path: str) -> bool:

        try:
            final_path = self.base_path / Path(path)
            final_path.mkdir()
            return True
        except (FileNotFoundError, FileExistsError) as e:
            return False

    async def create_folder(self, path: str) -> bool:
        return await self.create_dir(path)

    @run_in_executor
    def create_file_inner(self, path: str) -> None:
        final_path = self.base_path / Path(path)
        final_path.touch()

    async def create_file(self, path: str) -> None:
        await self.create_file_inner(path)

    @run_in_executor
    def append_to_file_inner(self, path: str, content: str) -> None:
        
        final_path = self.base_path / Path(path)

        with final_path.open('a') as f:
            f.write(content)

    async def append_to_file(self, path: str, content: str) -> None:
        await self.append_to_file_inner(path, content)

    @run_in_executor
    def read_file_inner(self, path) -> str:
        final_path = self.base_path / Path(path)
        return final_path.read_text()

    async def read_file(self, path: str) -> str:
        return await self.read_file_inner(path)
