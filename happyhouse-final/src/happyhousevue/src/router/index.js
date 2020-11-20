import Vue from 'vue';
import VueRouter from 'vue-router';
import QnA from '../views/QnABoard.vue';
import Login from '../views/Login.vue';
import Me from '../views/Me.vue';
import store from '../store';

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (store.getters.getAccessToken) {
    return next();
  } else next('/happyhouse/login' + nextRoute);
};

const routes = [
  {
    path: '/happyhouse/qna',
    name: 'QnA',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: QnA,
  },
  {
    path: '/happyhouse/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/happyhouse/login/:nextRoute',
    name: 'LoginNextRoute',
    component: Login,
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
