import * as chai from 'chai';
import 'mocha';
import { FileService } from "../../src/services/FileService";
import { Container } from "typedi";

describe("FileServiceTest", () => {

    const fileService = Container.get(FileService);

    it("should retrieve files array", () => {

        const files = fileService.getFileInfo();
        chai.expect(files.length).to.be.eql(2);

    });

});
