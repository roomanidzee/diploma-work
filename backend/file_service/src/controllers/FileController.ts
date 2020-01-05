import {Request, Response} from "express";
import {Controller, Req, Res, Get} from "routing-controllers"

@Controller()
export class FileController {

    @Get("/files/list")
    listFiles(@Req() request: Request, @Res() response: Response) {
        return response.send("Files list!");
    }

}
