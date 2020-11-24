<template>
  <div class="ma-auto">
    <v-card max-width="500" class="px-10 pb-5">
      <v-card-title sub class="subtitle-1 justify-center pb-0">
        집중할 준비, 되셨나요?
      </v-card-title>
      <v-card-title sub class="my-2 justify-center pa-0">
        <h2>"로그인"</h2>
      </v-card-title>
      <v-card-text>
        <v-form v-model="valid">
          <v-text-field
            v-model="user.userid"
            :rules="idRules"
            :counter="20"
            label="아이디"
            required
          ></v-text-field>
          <v-text-field
            type="Password"
            v-model="user.userpwd"
            :rules="passwordRules"
            label="패스워드"
            required
            @keypress.enter="login"
          ></v-text-field>
          <v-btn depressed color="primary" @click="login">
            로그인
          </v-btn>
          <v-btn class="ml-6" depressed color="success" @click="join">
            회원가입
          </v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export default {
  name: 'Login',
  data: () => ({
    user: {
      userid: '',
      userpwd: '',
    },
    message: '',
    valid: false,
    idRules: [
      (v) => !!v || 'id is required',
      (v) => v.length <= 20 || 'id must be less than 20 characters',
    ],
    passwordRules: [(v) => !!v || 'password is required'],
  }),

  computed: {
    nextRoute() {
      // this.$router.params.nextRoute =
      //   '/happyhouse/' + this.$route.params.nextRoute;
      // alert(this.$route.params.firstKey + '/' + this.$route.params.secondKey);
      let _this = this;
      // if (_this.$router.params.thirdKey) {
      //   alert(
      //     _this.$route.params.firstKey +
      //       '/' +
      //       _this.$route.params.secondKey +
      //       '/' +
      //       _this.$router.params.thirdKey
      //   );
      //   return (
      //     _this.$route.params.firstKey +
      //     '/' +
      //     _this.$route.params.secondKey +
      //     '/' +
      //     _this.$router.params.thirdKey
      //   );
      // }

      if (_this.$route.params.thirdKey) {
        alert(
          'after mix 3 :' +
            _this.$route.params.firstKey +
            '/' +
            _this.$route.params.secondKey +
            '/' +
            _this.$route.params.thirdKey
        );
        return (
          _this.$route.params.firstKey +
          '/' +
          _this.$route.params.secondKey +
          '/' +
          _this.$route.params.thirdKey
        );
      } else if (_this.$route.params.secondKey) {
        alert(
          'after mix 2 :' +
            _this.$route.params.firstKey +
            '/' +
            _this.$route.params.secondKey
        );
        return (
          _this.$route.params.firstKey + '/' + _this.$route.params.secondKey
        );
      } else {
        alert('아무고토 없어요 else ');
        return '';
      }

      // if(this.$router.params.thirdKey){
      //   return this.$route.params.firstKey + '/' + this.$route.params.secondKey + '/' + this.$router.params.thirdKey;
      // }
    },
  },

  methods: {
    login: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      // alert('hello');
      // alert(this.nextRoute + ' hello');
      alert('end hello');
      this.$store
        .dispatch('LOGIN', this.user)
        .then(() => {
          alert(this.nextRoute + ' nextRoute recpog');
          this.$router.replace(`/${this.nextRoute}`);
        })
        .catch(({ message }) => (this.msg = message));
    },
  },
};
</script>

<style scope>
#login-div {
  text-align: center;
}
</style>
