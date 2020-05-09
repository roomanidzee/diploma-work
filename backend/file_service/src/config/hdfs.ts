const config = require('config-yml');

const hdfs = require('webhdfs');

const hdfsClient = hdfs.createClient(
    {
        host: config.hdfs.host,
        port: config.hdfs.http_port
    }
);

export {
    hdfsClient,
};