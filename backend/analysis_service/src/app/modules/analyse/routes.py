
from fastapi import (
    APIRouter,
    Request,
    responses
)

from app.modules.analyse.services import DBService
from app.modules.auth.services import AuthService

router = APIRouter()

@router.get("/{file_id}/validate")
async def validate_file(file_id: str, request: Request) -> responses.JSONResponse:

    auth_service = AuthService()
    service = DBService()
    token = request.headers.get('Authorization', None)

    auth_check = await auth_service.check_for_user(token)

    if not auth_check:
        return responses.JSONResponse(
            content={'exists': False},
            status_code=400
        )

    check_result = await service.check_for_file(token, file_id)

    if not check_result:
        return responses.JSONResponse(
            content={'exists': False},
            status_code=400
        )
    
    return responses.JSONResponse(
        content={'exists': True},
        status_code=200
    )
