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
      // console.log(state.accessToken + ' is nice');
      if (state.accessToken !== null) return state.accessToken;

      // if (state.accessToken == null && localStorage != undefined)
      //   return localStorage.accessToken;
      // console.log(state.accessToken);
      // console.log(state.accessToken || localStorage.accessToken);
      // console.log('반전 of null' + !null);
      // console.log('반전 of undefined' + !undefined);
      // console.log(localStorage.accessToken);

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
      // console.log(localStorage.userId);
      // console.log('mutation ' + state);
      // console.log('mutation ' + localStorage.accessToken);
      // const enhanceAccessToeken = () => {
      //   const { accessToken } = localStorage;
      //   if (!accessToken) return;
      //   axios.defaults.headers.common['auth-token'] = payload['auth-token'];
      // };
      // enhanceAccessToeken();
    },
    LOGOUT(state) {
      state.accessToken = null;
      delete localStorage.accessToken;
      delete localStorage.userId;
      delete localStorage.userName;
      // localStorage.accessToken = null;
      state.userId = '';
      state.userName = '';
    },
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/user/confirm/login`, user)
        .then((response) => {
          context.commit('LOGIN', response.data);
          axios.defaults.headers.common[
            'auth-token'
          ] = `${response.data['auth-token']}`;
        })
        .catch(({ message }) => alert(message));
    },
    LOGOUT(context) {
      // console.log(this.state.accessToken);
      axios.defaults.headers.common['auth-token'] = undefined;
      context.commit('LOGOUT');
    },
  },
  modules: {},
});
