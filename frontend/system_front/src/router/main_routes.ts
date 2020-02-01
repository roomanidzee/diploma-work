const Home = () => import(/* webpackChunkName: "group-main" */ '../views/main/Home.vue');
const About = () => import(/* webpackChunkName: "group-main" */ '../views/about/About.vue');

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
