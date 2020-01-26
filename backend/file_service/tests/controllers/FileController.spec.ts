import app from "../../src/config/express";
import * as chai from 'chai';
import chaiHttp = require('chai-http');
import 'mocha';

chai.use(chaiHttp);

describe("FileControllerTest", () => {

    it("should retrieve file list", (done) => {

        chai.request(app)
            .get("/api/files/list")
            .end((err, res) => {
                chai.expect(res.status).to.be.eql(200);
                chai.expect(res.body).not.to.be.empty;

                let firstFile: string = res.body[0]["path"];
                let secondFile: string = res.body[1]["path"];

                chai.expect(firstFile.substring(
                    firstFile.length - 7,
                    firstFile.length
                )).to.be.eql("gitkeep");

                chai.expect(secondFile.substring(
                    secondFile.length - 8,
                    secondFile.length
                )).to.be.eql("gitkeepe");

                done();
            });        

    });

});
