import "reflect-metadata";
import { useExpressServer, getMetadataArgsStorage } from "routing-controllers";
import { routingControllersToSpec } from 'routing-controllers-openapi';
import { FileController } from "../controllers/FileController";
import express = require('express');
import fileUpload = require('express-fileupload');
import * as httpLogger from 'morgan';
import logger from './logger';
import * as path from "path";
import * as bodyParser from 'body-parser';

const swaggerUI = require('swagger-ui-express');
const config = require('config-yml');
const app = express();

app.use(httpLogger('dev'));
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(bodyParser.json());
app.use(require('express-status-monitor')());
app.use(express.static(path.resolve('storage')));
app.use(fileUpload());

const controllersOptions = {
    routePrefix: "/api",
    controllers: [FileController]
};

const apiOptions = {
    info: {
        description: 'Studeeper-Backend File Service API Definition',
        title: 'File Service API',
        version: '0.0.1'
    }
};

const docsUIOptions = {
    swaggerOptions: {
        url: `http://${config.server.host}:${config.server.port}/api/docs`
    }
};

useExpressServer(app, controllersOptions);

const apiStorage = getMetadataArgsStorage();

const apiSpec = routingControllersToSpec(
    apiStorage,
    controllersOptions,
    apiOptions
);

app.get('/api/docs', (_req, res) => {
    res.json(apiSpec);
});

app.use('/api/docs/ui', swaggerUI.serve, swaggerUI.setup(null, docsUIOptions));

app.use((error, req, res, next) => {

    logger.error(error.message);

    if(!error.statusCode){
        error.statusCode = 500;
    }

    const date = new Date();
    const options = {  
        weekday: "long", year: "numeric", month: "short",  
        day: "numeric", hour: "2-digit", minute: "2-digit"  
    };

    res.status(error.statusCode)
       .json({
        status_code: error.statusCode,
        error_time: date.toLocaleTimeString("ru-ru", options),
        message: "An error occured",
        debug_message: error.message
    });

});

export default app;
