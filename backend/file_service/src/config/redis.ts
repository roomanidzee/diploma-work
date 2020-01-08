const config = require('config-yml');
import * as redis from 'redis';
import logger from './logger';

const redis_client = redis.createClient({
        host: config.redis.host,
        port: config.redis.port,
        db: config.redis.db
    }
);

redis_client.on("error", function (err) {
    logger.error(`Redis Error: ${err}`);
});

redis_client.on('connect', function(){
    logger.info("Connection to Redis established");
});

export default redis_client;
