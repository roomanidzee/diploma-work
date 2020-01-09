import { Service } from "typedi";
import { Request } from "express";
import logger from '../config/logger';
import * as fs from "fs";
import * as path from "path";
import base_path from "../config/files";
import { RedisService } from './RedisService';
import { MongoService, FileObject } from './MongoService';

type FileInfo = {
    folder: string,
    files: FileInfo[] | string
}

@Service()
export class FileService {

    constructor(
        private mongoService: MongoService,
        private redisService: RedisService
    ) { }

    uploadFile(req: Request, fileObj: any): FileObject {

        const userID: string = this.redisService.validateRequest(req);

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

            let files: string[] = [];
            const folderName = path.basename(dir);
            
            const dirFiles = fs.readdirSync(dir);

            dirFiles.forEach((dirFile) => {

                const filePath = path.join(dir, dirFile);
                const fileStat = fs.lstatSync(filePath);
                
                if(fileStat.isDirectory()){
                    result.push({
                        folder: dir,
                        files: this.listFiles(filePath)
                    });
                }else{
                    result.push({
                        folder: dir,
                        files: filePath
                    })
                }
            });

        });

        return result;

    }

}