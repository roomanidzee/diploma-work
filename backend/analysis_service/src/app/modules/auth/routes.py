
from fastapi import (
    APIRouter,
    Request,
    responses
)
from app.modules.auth.services import AuthService

router = APIRouter()

@router.get("/validate")
async def validate_user(request: Request) -> responses.JSONResponse:

    service = AuthService()

    check_result = await service.check_for_user(request.headers.get('Authorization', None))

    if check_result:
        return responses.JSONResponse(
            content={'is_user': True},
            status_code=200
        )
    else:
        return responses.JSONResponse(
            content={'is_user': False},
            status_code=400
        )