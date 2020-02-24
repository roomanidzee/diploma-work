const PeopleInfo = () => import(/* webpackChunkName: "group-people" */ '../views/people/PeopleInfo.vue');

const peopleRoutes = [
  {
    path: '/people',
    name: 'people',
    component: PeopleInfo,
  },
];

export default peopleRoutes;
