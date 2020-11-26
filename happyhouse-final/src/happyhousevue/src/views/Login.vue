<template>
  <div class="mt-15 mx-auto">
    <v-card min-width="500" class="mt-15 px-10 py-5">
      <v-card-title sub class="subtitle-1 justify-center pb-0">
        집중할 준비, 되셨나요?
      </v-card-title>
      <v-card-title sub class="my-2 justify-center pa-0">
        <h2>"로그인"</h2>
      </v-card-title>
      <v-card-text>
        <v-form v-model="valid" style="text-align:center">
          <v-text-field
            v-model="user.userid"
            :rules="idRules"
            :counter="20"
            label="아이디"
            required
          ></v-text-field>
          <v-text-field
            class="mb-10"
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
import swal from 'vue-swal';

Vue.use(VueRouter);

Vue.use(swal);
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
      let _this = this;

      if (_this.$route.params.thirdKey) {
        return (
          _this.$route.params.firstKey +
          '/' +
          _this.$route.params.secondKey +
          '/' +
          _this.$route.params.thirdKey
        );
      } else if (_this.$route.params.secondKey) {
        return (
          _this.$route.params.firstKey + '/' + _this.$route.params.secondKey
        );
      } else {
        return '';
      }
    },
  },

  methods: {
    login: function() {
      this.$store
        .dispatch('LOGIN', this.user)
        .then((response) => {
          if (response == 'success') {
            this.$swal('로그인 완료 :)', '', 'success');
          } else {
            this.$swal('로그인 실패 :)', '', 'error');
          }

          this.$router.replace(`/${this.nextRoute}`);
        })
        .catch(({ message }) => (this.msg = message));
    },
    join: function() {
      this.$router.replace('/happyhouse/join');
    },
  },
};
</script>

<style scope>
#login-div {
  text-align: center;
}
</style>
