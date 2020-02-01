const Login = () => import(/* webpackChunkName: "group-auth" */ '../views/auth/Login.vue');
const Register = () => import(/* webpackChunkName: "group-auth" */ '../views/auth/Register.vue');

const authRoutes = [
  {
    path: '/login',
    name: 'login_page',
    component: Login,
  },
  {
    path: '/register',
    name: 'register_page',
    component: Register,
  },
];

export default authRoutes;
