const Home = () => import(/* webpackChunkName: "group-main" */ '../views/main/Home.vue');
const About = () => import(/* webpackChunkName: "group-main" */ '../views/about/About.vue');

const Profile = () => import(/* webpackChunkName: "group-profile" */ '../views/main/Profile.vue');
const Info = () => import(/* webpackChunkName: "group-profile" */ '../views/main/InfoView.vue');

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
  {
    path: '/profile',
    name: 'profile',
    component: Profile,
  },
  {
    path: '/info',
    name: 'info_test',
    component: Info,
  },
];

export default mainRoutes;
