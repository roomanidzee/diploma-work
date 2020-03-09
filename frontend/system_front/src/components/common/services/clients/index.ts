import AppClientImpl from '@/components/common/services/clients/AppClientImpl';

const config = require('config-yml');

const serviceAppURL = `http://${config.service_app.host}:${config.service_app.port}`;
const filesAppURL = `http://${config.files_app.host}:${config.files_app.port}}`;

const serviceAppClient = new AppClientImpl(serviceAppURL);
const filesAppClient = new AppClientImpl(filesAppURL);

export {
  serviceAppClient, filesAppClient,
};
