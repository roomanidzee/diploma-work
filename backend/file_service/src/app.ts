import "reflect-metadata";
import { useExpressServer } from "routing-controllers";
import { FileController } from "./controllers/FileController";
import app from './config/configure';

useExpressServer(app, {
    routePrefix: "/api",
    controllers: [FileController]
})

const config = require('config-yml');
app.listen(config.server.port, function () {
    console.log("app launched on port " + config.server.port)
});
