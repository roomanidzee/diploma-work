import { Request, Response } from "express";
import { Container } from "typedi";
import { Controller, Req, Res, Get, Post } from "routing-controllers"
import { FileService } from "../services/FileService";
import { RedisService} from "../services/RedisService";
import { KafkaService } from "../services/KafkaService";

@Controller()
export class FileController {

    @Get("/files/list")
    listFiles(@Req() req: Request, @Res() resp: Response) {
        const fileService = Container.get(FileService);

        return resp.json(fileService.getFileInfo());

    }

    @Post("/files/upload")
    createFile(@Req() req: Request, @Res() resp: Response) {

        const fileService = Container.get(FileService);
        const redisService = Container.get(RedisService);

        const userID = redisService.validateRequest(req);
        
        return resp.json(fileService.uploadFile(userID, req.files.input_file));

    }

    @Post("/facilities/uploadStudents")
    uploadStudents(@Req() req: Request, @Res() resp: Response) {

        const kafkaService = Container.get(KafkaService);
        const redisService = Container.get(RedisService);

        const userID = redisService.validateRequest(req);

        return resp.json(kafkaService.uploadStudents(userID, req.files.input_file));

    }

}
