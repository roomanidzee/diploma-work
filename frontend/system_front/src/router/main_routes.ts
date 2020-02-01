const Home = () => import(/* webpackChunkName: "group-main" */ '../views/Home.vue');
const About = () => import(/* webpackChunkName: "group-main" */ '../views/About.vue');

const mainRoutes = [
  {
    path: '/',
    name: 'home',
    component: Home,
  },
  {
    path: '/about',
    name: 'about',
    component: About,
  },
];

export default mainRoutes;
