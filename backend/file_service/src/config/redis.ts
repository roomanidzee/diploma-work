const config = require('config-yml');
import * as redis from 'redis';
import logger from './logger';

const client = redis.createClient({
        host: config.redis.host,
        port: config.redis.port,
        db: config.redis.db
    }
);

client.on("error", function (err) {
    logger.error(`Redis Error: ${err}`);
});

export default client;
