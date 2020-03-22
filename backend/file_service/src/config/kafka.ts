const config = require('config-yml');
import { KafkaClient } from 'kafka-node';

const kafkaClient = new KafkaClient(
    {
        kafkaHost: `${config.kafka.host}:${config.kafka.port}`;
    }
);

export {
    kafkaClient,
};