
from fastapi import (
    APIRouter,
    Request,
    responses
)

from app.modules.analyse.services import DBService, AnalysisService
from app.modules.auth.services import AuthService

router = APIRouter()

@router.get("/{file_id}/validate")
async def validate_file(file_id: str, request: Request) -> responses.JSONResponse:

    auth_service = AuthService()
    token = request.headers.get('Authorization', None)

    auth_check = await auth_service.check_for_user(token)

    if not auth_check:
        return responses.JSONResponse(
            content={'exists': False},
            status_code=400
        )

    service = DBService()
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

@router.get("/{file_id}/check_model")
async def check_for_model(file_id: str, request: Request) -> responses.JSONResponse:

    auth_service = AuthService()
    token = request.headers.get('Authorization', None)

    auth_check = await auth_service.check_for_user(token)

    if not auth_check:
        return responses.JSONResponse(
            content={'exists': False},
            status_code=400
        )

    service = AnalysisService()
    check_result = await service.check_for_model(file_id)

    if not check_result:
        return responses.JSONResponse(
            content={'exists': False},
            status_code=400
        )
    
    return responses.JSONResponse(
        content={'exists': True},
        status_code=200
    )

@router.get('/launch_models_transfer')
async def launch_transfer(show_status: bool, request: Request) -> responses.JSONResponse:

    auth_service = AuthService()
    token = request.headers.get('Authorization', None)

    auth_check = await auth_service.check_for_user(token)

    if not auth_check:
        return responses.JSONResponse(
            content={'status': 'job cancelled'},
            status_code=400
        )

    service = AnalysisService()
    job_launch_info = service.transfer_models(show_status)

    if job_launch_info:
        return responses.JSONResponse(
            content={'status': job_launch_info},
            status_code=200
        )
    else:
        return responses.JSONResponse(
            content={},
            status_code=200
        )

@router.get('/{file_id}/launch_base_analysis')
async def launch_base_analysis(file_id: str, request: Request) -> responses.JSONResponse:

    auth_service = AuthService()
    token = request.headers.get('Authorization', None)

    auth_check = await auth_service.check_for_user(token)

    if not auth_check:
        return responses.JSONResponse(
            content={'status': 'job cancelled'},
            status_code=400
        )

    service = AnalysisService()

    return responses.JSONResponse(
        content=service.launch_facility_analyse(file_id)
    )

@router.get('/{file_id}/predict')
async def launch_predict(file_id: str, request: Request) -> responses.JSONResponse:

    auth_service = AuthService()
    token = request.headers.get('Authorization', None)

    auth_check = await auth_service.check_for_user(token)

    if not auth_check:
        return responses.JSONResponse(
            content={'status': 'job cancelled'},
            status_code=400
        )

    service = AnalysisService()

    return responses.JSONResponse(
        content={
            'result': service.launch_analyse(file_id)
        }
    )
