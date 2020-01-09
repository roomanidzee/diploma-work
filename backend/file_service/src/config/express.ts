import express = require('express');
import fileUpload = require('express-fileupload');
import * as logger from 'morgan';
import * as path from "path";
import * as bodyParser from 'body-parser';
const app = express();

app.use(logger('dev'));
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(bodyParser.json());
app.use(require('express-status-monitor')());
app.use(express.static(path.resolve('storage')));
app.use(fileUpload());

export default app;
