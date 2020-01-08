import { Service } from "typedi";
import { Request } from "express";
import logger from '../config/logger';
import * as fs from "fs";
import * as path from "path";
import base_path from "../config/files";
import { RedisService } from './RedisService';
import { MongoService, FileObject } from './MongoService';

type FolderInfo = {
    user_id: string,
    files: string[]
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

        const dirPath = `${userPath}/${fileObj.mimetype}`;

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

    listFiles(): FolderInfo[] {

        let result: FolderInfo[] = [];

        const directories = fs.readdirSync(base_path);

        directories.forEach((dir) => {

            let files: string[] = [];
            const userID = path.basename(dir);
            
            const dirFiles = fs.readdirSync(dir);

            dirFiles.forEach((dirFile) => {
                files.push(dirFile);
            });

            result.push({
                user_id: userID,
                files: files
            });

        });

        return result;

    }

}