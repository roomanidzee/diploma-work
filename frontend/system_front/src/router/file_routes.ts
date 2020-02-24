const FileInfo = () => import(/* webpackChunkName: "group-files" */ '../views/files/FilesInfo.vue');

const filesRoutes = [
  {
    path: '/files',
    name: 'files',
    component: FileInfo,
  },
];

export default filesRoutes;
