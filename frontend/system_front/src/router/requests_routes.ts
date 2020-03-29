const RequestsInfo = () => import(/* webpackChunkName: "group-requests" */ '../views/requests/RequestsInfo.vue');
const RequestSingleInfo = () => import(/* webpackChunkName: "group-requests" */ '../views/requests/RequestInformation.vue')

const requestsRoutes = [
  {
    path: '/requests',
    name: 'requests',
    component: RequestsInfo,
  },
  {
    path: '/request-info',
    name: 'request-info',
    component: RequestSingleInfo
  }
];

export default requestsRoutes;
