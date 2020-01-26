import app from './config/express';
import logger from './config/logger';
const config = require('config-yml');

app.listen(config.server.port, function () {
    logger.info(`app launched on port ${config.server.port}`)
});
