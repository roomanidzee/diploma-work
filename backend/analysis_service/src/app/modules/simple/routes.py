from fastapi import APIRouter, HTTPException

router = APIRouter()

@router.get("/ping")
async def pong():
    return {"ping": "pong!"}