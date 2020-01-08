import "reflect-metadata";
import { useExpressServer } from "routing-controllers";
import { FileController } from "./controllers/FileController";
import app from './config/configure';
import logger from './config/logger';

useExpressServer(app, {
    routePrefix: "/api",
    controllers: [FileController]
})

const config = require('config-yml');
app.listen(config.server.port, function () {
    logger.info("app launched on port " + config.server.port)
});
