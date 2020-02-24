import Vue from 'vue';
import VueRouter from 'vue-router';

import mainRoutes from './main_routes';
import authRoutes from './auth_routes';
import fileRoutes from './file_routes';
import peopleRoutes from './people_routes';
import requestsRoutes from './requests_routes';

Vue.use(VueRouter);

const routes = [
  ...mainRoutes,
  ...authRoutes,
  ...fileRoutes,
  ...peopleRoutes,
  ...requestsRoutes,
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
