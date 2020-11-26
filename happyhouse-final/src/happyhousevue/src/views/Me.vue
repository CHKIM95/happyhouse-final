<template>
  <div class="mt-15 mx-auto" style="min-width:600px">
    <v-card class="mt-15 px-10 pb-5" style="text-align:center">
      <v-card-title sub class="my-2 justify-center pt-5 mt-5">
        <h2>"마이 페이지"</h2>
      </v-card-title>
      <v-form v-model="valid">
        <v-container>
          <v-text-field
            v-model="user.userid"
            :rules="idRules"
            :counter="20"
            label="아이디"
            disabled
            required
          ></v-text-field>
          <v-text-field
            v-model="user.userpwd"
            type="Password"
            :rules="passwordRules"
            label="패스워드 변경"
            required
            placeholder="비밀번호를 입력해주세요"
          ></v-text-field>
          <v-text-field
            v-model="confirmPassword"
            type="Password"
            :rules="[user.userpwd === confirmPassword || 'Password must match']"
            label="패스워드 확인"
            required
            placeholder="비밀번호를 확인해주세요"
          ></v-text-field>
          <v-text-field
            v-model="user.username"
            :rules="nameRules"
            label="사용자 이름"
            required
            placeholder="이름을 입력해주세요"
          ></v-text-field>
          <v-text-field
            dense
            v-model="user.email"
            :rules="emailRules"
            label="이메일"
            required
            placeholder="이메일을 입력해주세요"
          ></v-text-field>
          <v-row>
            <v-col cols="6" class="pr-1">
              <v-text-field
                id="userAddressCode"
                label="우편번호"
                required
                disabled
                placeholder="우편번호를 검색해주세요"
              ></v-text-field>
            </v-col>
            <v-col cols="2" class="mt-2 pl-0 pb-0 mb-0">
              <v-btn
                class="float:left; margin-right:20px; margin-top:20px; clear:both;"
                primary
                @click="searchAddress"
              >
                검색
              </v-btn>
            </v-col>
          </v-row>
          <v-text-field
            v-model="user.address"
            id="userRoadAddress"
            label="도로명 주소"
            required
            disabled
            placeholder="도로명 주소"
          ></v-text-field>
          <v-btn color="success" @click="updateUser">
            회원 정보 수정
          </v-btn>
          <v-space></v-space>
          <v-btn color="error" class="ml-4" @click="deleteUser">
            회원 탈퇴
          </v-btn>
        </v-container>
      </v-form>
    </v-card>
  </div>
</template>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
import axios from 'axios';

import Vue from 'vue';
import swal from 'vue-swal';
Vue.use(swal);
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
    nameRules: [(v) => !!v || 'name is required'],
    emailRules: [
      (v) => !!v || 'E-mail is required',
      (v) => /.+@.+/.test(v) || 'E-mail must be valid',
    ],
  }),

  created() {
    axios
      .get(`${SERVER_URL}/user/info`)
      .then((response) => {
        // console.log(this.user);
        this.user = response.data.user;
        // console.log(this.user);
      })
      .catch(() => {
        this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
      });
  },

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

    updateUser: function() {
      this.user.address = document.getElementById('userRoadAddress').value;
      axios
        .put(`${SERVER_URL}/user/update`, {
          userid: this.user.userid,
          userpwd: this.user.userpwd,
          username: this.user.username,
          email: this.user.email,
          address: this.user.address,
        })
        .then((response) => {
          if (response.data == 'success') {
            this.$store.dispatch('LOGIN', this.user).then((response) => {
              if (response == 'success') {
                this.$swal('수정되었습니다', '', 'success');
              } else {
                this.$swal('수정 실패 :)', '', 'error');
              }
              // alert(this.user.username);
              this.$router.replace('/');
            });
          } else {
            this.$swal('수정에 실패하였습니다.', '', 'error');
          }
        });
    },

    deleteUser: function() {
      axios
        .delete(`${SERVER_URL}/user/delete?userid=${this.user.userid}`)
        .then((response) => {
          if (response.data == 'success') {
            this.$swal('탈퇴되었습니다', '', 'success');
            this.$store
              .dispatch('LOGOUT')
              .then(() => this.$router.replace('/').catch(() => {}));
          } else {
            this.$swal('탈퇴에 실패하였습니다.', '', 'error');
          }
        });
    },
  },
};
</script>
