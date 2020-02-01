import Vue from 'vue';
import VueRouter from 'vue-router';

import mainRoutes from './main_routes';
import authRoutes from './auth_routes';

Vue.use(VueRouter);

const routes = [
  ...mainRoutes,
  ...authRoutes,
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
