import { Request, Response } from "express";
import { Container } from "typedi";
import { Controller, Req, Res, Get, Post } from "routing-controllers"
import { FileService } from "../services/FileService";

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

        //@ts-ignore
        return resp.json(fileService.uploadFile(req, req.files.input_file));

    }

}
