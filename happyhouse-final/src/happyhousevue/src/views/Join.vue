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
            v-model="user.userpwd"
            type="Password"
            :rules="passwordRules"
            label="패스워드 입력"
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
            v-model="user.username"
            :rules="nameRules"
            label="사용자 이름"
            required
            placeholder="이름을 입력해주세요"
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
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="8" md="4">
          <v-text-field
            id="userAddressCode"
            label="우편번호"
            required
            placeholder="우편번호를 검색해주세요"
          ></v-text-field>
        </v-col>
        <v-col cols="4" md="4">
          <v-btn primary @click="searchAddress">
            검색
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="4">
          <v-text-field
            v-model="user.address"
            id="userRoadAddress"
            label="도로명 주소"
            required
            placeholder="도로명 주소"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-btn depressed color="primary" @click="joinUser">
          회원 가입
        </v-btn>
      </v-row>
    </v-container>
  </v-form>
</template>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data: () => ({
    user: {
      userid: '',
      username: '',
      userpwd: '',
      email: '',
      address: '',
    },
    valid: false,
    confirmPassword: '',
    idRules: [
      (v) => !!v || 'id is required',
      (v) => v.length <= 20 || 'id must be less than 20 characters',
    ],
    passwordRules: [(v) => !!v || 'password is required'],
    nameRules: [(v) => !!v || 'name is required'],
    emailRules: [
      (v) => !!v || 'E-mail is required',
      (v) => /.+@.+/.test(v) || 'E-mail must be valid',
    ],
  }),

  methods: {
    searchAddress: function() {
      new daum.Postcode({
        oncomplete: function(data) {
          let codeArea = document.getElementById('userAddressCode');
          let addressArea = document.getElementById('userRoadAddress');
          codeArea.value = data.zonecode;
          addressArea.value = data.roadAddress;
        },
      }).open();
    },
    joinUser: function() {
      this.user.address = document.getElementById('userRoadAddress').value;
      axios
        .post(`${SERVER_URL}/user/join`, {
          userid: this.user.userid,
          userpwd: this.user.userpwd,
          username: this.user.username,
          email: this.user.email,
          address: this.user.address,
        })
        .then((response) => {
          if (response.data == 'success') {
            alert('가입되었습니다.');
            this.$router.replace('/');
          } else {
            alert('가입에 실패하였습니다.');
          }
        });
    },
  },
};
</script>
