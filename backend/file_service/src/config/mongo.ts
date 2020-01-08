const config = require('config-yml');
import { connect, model, Document, Schema } from "mongoose";
import logger from './logger';

const mongo_config = config.mongo

const mongoCreds = `${mongo_config.user}:${mongo_config.password}`
const mongoConn = `${mongo_config.host}:${mongo_config.port}/${mongo_config.database}`

const mongoURI = `mongo://${mongoCreds}@${mongoConn}`

connect(mongoURI, (err: any) => {
    if (err) {
      logger.error(`MongoDB connection error: ${err.message}`);
    } else {
      logger.info("Connection to MongoDB established");
    }
});

export interface FileInfoInterface extends Document{
    user_id: string,
    path: string,
    type: string,
    size: number
};

export const FileInfoSchema = new Schema({
    user_id: {
        type: String,
        required: true
    },
    path: {
        type: String,
        required: true
    },
    type: {
        type: String,
        required: true
    },
    size: {
        type: Number,
        required: true
    }
});

const FileInfo = model<FileInfoInterface>("FileInfo", FileInfoSchema, "files");
export default FileInfo;
