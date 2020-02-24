const RequestsInfo = () => import(/* webpackChunkName: "group-requests" */ '../views/requests/RequestsInfo.vue');

const requestsRoutes = [
  {
    path: '/requests',
    name: 'requests',
    component: RequestsInfo,
  },
];

export default requestsRoutes;
