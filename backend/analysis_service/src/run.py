
import uvicorn

from app.launch import config_output

if __name__ == '__main__':
    uvicorn.run('app.launch:app', host=config_output.host, port=config_output.port, reload=True)