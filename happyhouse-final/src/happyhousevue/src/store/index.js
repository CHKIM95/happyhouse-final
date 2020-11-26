import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    accessToken: null,
    userId: '',
    userName: '',
  },
  getters: {
    getAccessToken(state) {
      if (state.accessToken !== null) return state.accessToken;

      return localStorage.accessToken;
    },
    getUserId(state) {
      if (state.userId !== '') return state.userId;
      return localStorage.userId;
    },
    getUserName(state) {
      if (state.userName !== '') return state.userName;
      return localStorage.userName;
    },
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload['auth-token'];
      state.userId = payload['user-id'];
      state.userName = payload['user-name'];
      localStorage.accessToken = payload['auth-token'];
      localStorage.userId = payload['user-id'];
      localStorage.userName = payload['user-name'];
    },
    LOGOUT(state) {
      state.accessToken = null;
      delete localStorage.accessToken;
      delete localStorage.userId;
      delete localStorage.userName;
      state.userId = '';
      state.userName = '';
    },
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/user/confirm/login`, user)
        .then((response) => {
          if (response.data.message == '로그인 실패') {
            return 'fail';
          } else {
            context.commit('LOGIN', response.data);
            axios.defaults.headers.common[
              'auth-token'
            ] = `${response.data['auth-token']}`;
            return 'success';
          }
        })
        .catch(({ message }) => alert(message));
    },
    LOGOUT(context) {
      axios.defaults.headers.common['auth-token'] = undefined;
      context.commit('LOGOUT');
    },
  },
  modules: {},
});
