import { Service } from "typedi";
import logger from '../config/logger';
import {kafkaClient} from '../config/kafka';
import { Producer, ProduceRequest} from 'kafka-node';
import * as fs from "fs";
import csv = require('csv-parser');
import { FileService } from './FileService';
import { FileObject } from './MongoService';

const config = require('config-yml');

type ExportResult = {
    message: string
}

type KafkaObject = {
    timestamp: number,
    username: string,
    raw_password: string,
    surname: string,
    name: string,
    patronymic: string,
    email: string,
    facility_title: string,
    facility_graduation: string,
    facility_speciality: string,
    facility_group: string
}

@Service()
export class KafkaService{

    constructor(
        private fileService: FileService
    ) {}

    uploadStudents(userID: string, fileObj: any): ExportResult{

        const fileObject: FileObject = this.fileService.uploadFile(userID, fileObj);

        const records: KafkaObject[] = [];

        fs.createReadStream(fileObject.path)
          .pipe(csv())
          .on('data', (data) =>{

            const record: KafkaObject  = {
                timestamp: Math.floor(Date.now() / 1000),
                username: data['username'],
                raw_password: data['raw_password'],
                surname: data['surname'],
                name: data['name'],
                patronymic: data['patronymic'],
                email: data['email'],
                facility_title: data['facility_title'],
                facility_graduation: data['facility_graduation'],
                facility_speciality: data['facility_speciality'],
                facility_group: data['facility_group']
            };

            records.push(record);

          })
          .on('end', () => {
              logger.info('Records for kafka prepared');
          });

        const kafkaProducer = new Producer(kafkaClient);
        const topics = config.kafka.topics;

        records.map(elem => JSON.stringify(elem));

        kafkaProducer.on('ready', () => {

            kafkaClient.refreshMetadata(
                topics,
                (err: Error) => {

                    if(err){
                        logger.error(`Kafka metadata refresh error: ${err}`);
                        throw err;
                    }

                    logger.info('Sending students info to Kafka');

                    kafkaProducer.send(
                        [
                            {
                                topic: topics[0],
                                messages: records
                            },
                            {
                                topic: topics[1],
                                messages: records
                            }
                        ],
                        (err: Error, result: ProduceRequest) => {
                            logger.debug(err || result);
                        }
                    );

                }
            )

        });

        kafkaProducer.on(
            'error',
            (err: Error): void => {
                logger.error('error', err);
                throw err;
            }
        );

        return {
            message: 'Successful data upload'
        }

    }

}
