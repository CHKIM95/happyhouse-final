<template>
  <div>
    <v-app-bar class="indigo lighten-1 white--text" dense dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title>Page title</v-toolbar-title>

      <v-btn icon right>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon right>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-tabs right>
        <v-tab v-if="!getAccessToken" to="/happyhouse/login">로그인</v-tab>
        <v-tab v-else to="/happyhouse/logout" @click.prevent="onClickLogout"
          >로그아웃</v-tab
        >
        <v-tab v-if="!getAccessToken" to="/happyhouse/join">회원가입</v-tab>
        <v-tab v-else to="/happyhouse/me">회원정보</v-tab>
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
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item>
            <v-list-item-title to="/happyhouse/qna">공지사항</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title to="/happyhouse/qna"
              >QnA 게시판</v-list-item-title
            >
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
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
    },
  },
};
</script>
