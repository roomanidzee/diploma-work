
from fastapi import (
    APIRouter,
    Request,
    responses
)

from app.modules.analyse.services import DBService

router = APIRouter()

@router.get("/{file_id}/validate")
async def validate_file(file_id: str, request: Request) -> responses.JSONResponse:

    service = DBService()
    token = request.headers.get('Authorization', None)
    check_result = await service.check_for_file(token, file_id)

    if check_result:
        return responses.JSONResponse(
            content={'exists': True},
            status_code=200
        )
    else:
        return responses.JSONResponse(
            content={'exists': False},
            status_code=400
        )
