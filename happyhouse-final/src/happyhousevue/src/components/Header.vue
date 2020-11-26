<template>
  <div>
    <v-app-bar color="#4181A6" class="white--text" dense dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-tab>
        <v-btn fab small color="cyan" to="/">
          <v-img
            position="center"
            max-height="30"
            max-width="30"
            src="@/assets/logo.png"
            class="float:left; margin-right:20px; margin-top:20px; clear:both;"
          ></v-img>
          <!-- <v-btn text @click="goHome">
          집중, My Home, Precious Home
        </v-btn> -->
        </v-btn>
      </v-tab>
      <v-tab>
        Happyhouse
      </v-tab>
      <!-- <v-btn icon right>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon right>
        <v-icon>mdi-magnify</v-icon>
      </v-btn> -->

      <v-tabs right>
        <v-tab v-if="!getAccessToken" to="/happyhouse/login">로그인</v-tab>
        <template v-else>
          <v-tab> {{ getUserId }} 님 환영합니다! </v-tab>
          <v-tab to="/happyhouse/logout" @click.prevent="onClickLogout"
            >로그아웃</v-tab
          >
        </template>
        <v-tab v-if="!getAccessToken" to="/happyhouse/join">회원가입</v-tab>
        <v-tab v-else to="/happyhouse/me">마이 페이지</v-tab>
      </v-tabs>

      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-for="n in 5" :key="n" @click="() => {}">
            <v-list-item-title>Option {{ n }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" absolute temporary>
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="deep-indigo--text text--accent-4"
        >
          <v-list-item to="/">
            홈으로 돌아가기
            <v-img
              position="center"
              max-height="30"
              max-width="30"
              class="float:left; margin-right:20px; margin-top:20px; clear:both;"
              src="@/assets/logo.png"
            ></v-img>
          </v-list-item>
          <v-list-item to="/happyhouse/qna">
            <v-list-item-title>QnA 게시판</v-list-item-title>
          </v-list-item>

          <v-list-item to="/happyhouse/notice">
            <v-list-item-title>Notice 게시판</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Fizz</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Buzz</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import axios from 'axios';
import Vue from 'vue';
import swal from 'vue-swal';
Vue.use(swal);

export default {
  data: () => ({
    drawer: false,
    group: null,
    isLogin: false,
  }),
  watch: {
    group() {
      this.drawer = false;
    },
  },
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName']),
  },
  methods: {
    goHome() {
      this.$router.push('/');
    },
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => {
          this.$swal('로그아웃 완료 :)', '', 'success');
          this.$router.replace('/');
        })
        .catch(() => {
          alert('error');
        });
      // this.$store.getters.getAccessToken();
    },
  },
  mounted() {
    this.$store.state.accessToken = this.getAccessToken;
    this.$store.state.userId = this.getUserId;
    this.$store.state.userName = this.getUserName;
    axios.defaults.headers.common['auth-token'] = this.getAccessToken;
  },
  // updated() {
  //   if (this.$store.getAccessToken) {
  //     this.isLogin = flase;
  //   } else {
  //     this.isLogin = false;
  //   }
  // },
};
</script>
