const Home = () => import(/* webpackChunkName: "group-main" */ '../views/main/Home.vue');
const About = () => import(/* webpackChunkName: "group-main" */ '../views/about/About.vue');

const Profile = () => import(/* webpackChunkName: "group-profile" */ '../views/main/Profile.vue');

const Search = () => import(/* webpackChunkName: "group-search" */ '../views/main/Search.vue');

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
    path: '/search',
    name: 'search-page',
    component: Search,
  },
];

export default mainRoutes;
