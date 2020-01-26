import { Service } from "typedi";
import mime = require('mime-types')
import logger from '../config/logger';
import * as fs from "fs";
import * as path from "path";
import base_path from "../config/files";
import { MongoService, FileObject } from './MongoService';

type FileInfo = {
    path: string,
    type: string,
    size: number
}

@Service()
export class FileService {

    constructor(
        private mongoService: MongoService
    ) { }

    uploadFile(userID: string, fileObj: any): FileObject {

        const userPath = `${base_path}/${userID}`;

        if (!fs.existsSync(userPath)) {
            fs.mkdirSync(userPath)
        }

        const mimeTypeCompare = {
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet": "xlsx",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.template": "docx",
            "application/pdf": "pdf",
            "text/csv": "csv"
        };

        const dirPath = `${userPath}/${mimeTypeCompare[fileObj.mimetype]}`;

        if (!fs.existsSync(dirPath)) {
            fs.mkdirSync(dirPath);
        }

        fileObj.mv(`${dirPath}/${fileObj.name}`, function (err) {

            if (err) {
                logger.error(`File Save error: ${err}`);
                throw err;
            }

        });

        return this.mongoService.saveFileInfo(userID, fileObj);

    }

    getFileInfo(): FileInfo[] {
        return this.listFiles(base_path);
    }

    private listFiles(dirPath: string): FileInfo[] {

        let result: FileInfo[] = [];

        const directories = fs.readdirSync(dirPath);

        directories.forEach((dir) => {
            const dirEntry = path.join(dirPath, dir);
            const fileStat = fs.statSync(dirEntry);

            if(fileStat.isDirectory()){
                result = result.concat(this.listFiles(dirEntry));
            }else{
                result.push({
                    path: dirEntry,
                    type: mime.lookup(dirEntry),
                    size: fs.statSync(dirEntry)["size"]
                })
            }

        });

        return result;

    }

}