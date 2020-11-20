<template>
  <v-form v-model="valid">
    <v-container>
      <v-row>
        <v-col cols="12" md="4">
          <v-text-field
            v-model="user.userid"
            :rules="idRules"
            :counter="20"
            label="아이디"
            required
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="4">
          <v-text-field
            type="Password"
            v-model="user.userpwd"
            :rules="passwordRules"
            label="패스워드 변경"
            required
            placeholder="비밀번호를 입력해주세요"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="4">
          <v-text-field
            v-model="confirmPassword"
            type="Password"
            :rules="[user.userpwd === confirmPassword || 'Password must match']"
            label="패스워드 확인"
            required
            placeholder="비밀번호를 확인해주세요"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="4">
          <v-text-field
            v-model="user.email"
            :rules="emailRules"
            label="이메일"
            required
            @keypress.enter="login"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-btn depressed color="primary" @click="updateUser">
          로그인
        </v-btn>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data: () => ({
    user: null,
    valid: false,
    confirmPassword: '',
    idRules: [
      (v) => !!v || 'id is required',
      (v) => v.length <= 20 || 'id must be less than 20 characters',
    ],
    passwordRules: [(v) => !!v || 'password is required'],
    emailRules: [
      (v) => !!v || 'E-mail is required',
      (v) => /.+@.+/.test(v) || 'E-mail must be valid',
    ],
  }),

  created() {
    axios
      .get(`${SERVER_URL}/user/info`)
      .then((response) => {
        this.user = response.data.user;
      })
      .catch(() => {
        this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
      });
  },
};
</script>
