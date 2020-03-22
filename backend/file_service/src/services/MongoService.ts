import {Service} from "typedi";
import logger from '../config/logger';
import base_path from '../config/files';
import FileInfo from '../config/mongo';

export type FileObject = {
    user_id: string,
    path: string,
    type: string,
    size: number
}

@Service()
export class MongoService{

    saveFileInfo(userID: string, inputFile: any): FileObject{

        const mimeTypeCompare = {
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet": "xlsx",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.template": "docx",
            "application/pdf": "pdf",
            "text/csv": "csv"
        };

        const objectData = {
            user_id: userID,
            path: `${base_path}/${userID}/${mimeTypeCompare[inputFile.mimetype]}/${inputFile.name}`,
            type: inputFile.mimetype,
            size: inputFile.size
        };

        const fileInfo = new FileInfo(objectData);

        fileInfo.save((err: any) => {

            if(err){
                logger.error(err);
                throw err;
            }

        });

        return objectData;

    }

}