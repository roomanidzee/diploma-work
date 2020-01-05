const yaml_config = require('config-yml');
import express = require('express');
import * as logger from 'morgan';
import * as bodyParser from 'body-parser';
const app = express();

app.use(logger('dev'));
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(bodyParser.json());
app.use(require('express-status-monitor')());

export default app;
