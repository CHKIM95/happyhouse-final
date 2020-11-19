import Vue from 'vue';
import VueRouter from 'vue-router';
import QnA from '../views/QnABoard.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/qna',
    name: 'QnA',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: QnA,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
