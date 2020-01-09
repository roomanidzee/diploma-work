import "reflect-metadata";
import { useExpressServer } from "routing-controllers";
import { FileController } from "./controllers/FileController";
import app from './config/express';
import logger from './config/logger';
const config = require('config-yml');

useExpressServer(app, {
    routePrefix: "/api",
    controllers: [FileController]
})

app.listen(config.server.port, function () {
    logger.info(`app launched on port ${config.server.port}`)
});
