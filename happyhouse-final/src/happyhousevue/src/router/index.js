import Vue from 'vue';
import VueRouter from 'vue-router';
import QnA from '../views/QnABoard.vue';
import UpdateQnA from '../components/UpdateQnA.vue';
import DetailQnA from '../components/DetailQnA.vue';
import InsertQnA from '../components/InsertQnA.vue';
import Notice from '../views/NoticeBoard.vue';
import Login from '../views/Login.vue';
import Me from '../views/Me.vue';
import Join from '../views/Join.vue';
import store from '../store';
import Main from '../views/Main.vue';
import HouseData from '../views/HouseData.vue';
import HouseDetailData from '../views/HouseDetailData.vue';
import GraphData from '../components/GraphData.vue';
import HospitalData from '../views/HospitalData.vue';
import ClinicData from '../views/ClinicData.vue';

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (store.getters.getAccessToken) {
    return next();
  } else {
    // console.log(nextRoute.substring(11));
    // return next('/happyhouse/login' + nextRoute.substring(11));
    return next('/happyhouse/login' + nextRoute);
  }
};

const routes = [
  {
    path: '/',
    name: 'Main',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    alias: '/happyhouse',
    component: Main,
  },

  {
    path: '/happyhouse/houseData',
    name: 'HouseData',
    beforeEnter: requireAuth(),
    component: HouseData,
  },

  {
    path: '/happyhouse/houseDetailData',
    name: 'HouseDetailData',
    component: HouseDetailData,
    beforeEnter: requireAuth(),
    props: true,
    children: [
      {
        path: 'graphData',
        name: 'GraphData',
        component: GraphData,
        props: true,
      },
      {
        path: 'hospitalData',
        name: 'HospitalData',
        component: HospitalData,
        props: true,
      },
      {
        path: 'clinicData',
        name: 'ClinicData',
        component: ClinicData,
        props: true,
      },
    ],
  },
  {
    path: '/happyhouse/qna',
    name: 'QnA',
    component: QnA,
  },
  {
    path: '/happyhouse/qna/insert',
    name: 'InsertQnA',
    component: InsertQnA,
    beforeEnter: requireAuth(),
  },

  {
    path: '/happyhouse/qna/:no',
    name: 'QnANo',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: QnA,
    props: true,
    children: [
      {
        path: 'detail',
        name: 'DetailQnA',
        component: DetailQnA,
        props: true,
      },
      {
        path: 'update',
        name: 'UpdateQnA',
        component: UpdateQnA,
        props: true,
      },
    ],
  },
  {
    path: '/happyhouse/notice',
    name: 'notice',
    beforeEnter: requireAuth(),
    component: Notice,
  },
  {
    path: '/happyhouse/login',
    name: 'Login',
    component: Login,
  },
  // {
  //   path: '/happyhouse/login/:nextRoute',
  //   name: 'LoginNextRoute',
  //   component: Login,
  // },

  {
    path: '/happyhouse/login/:firstKey/:secondKey',
    name: 'LoginNextRoute',
    component: Login,
  },
  {
    path: '/happyhouse/login/:firstKey/:secondKey/:thirdKey',
    name: 'LoginThirdRoute',
    component: Login,
  },

  {
    path: '/happyhouse/join',
    name: 'Join',
    component: Join,
  },
  {
    path: '/happyhouse/me',
    name: 'Me',
    component: Me,
    beforeEnter: requireAuth(),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
