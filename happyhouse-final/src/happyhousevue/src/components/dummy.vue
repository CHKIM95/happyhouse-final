<template>
  <div class="mt-15" style="width:800px">
    <v-card class="mt-15">
      <v-card-title sub class="my-2 justify-center pa-0">
        <h2>"회원정보 수정"</h2>
      </v-card-title>
      <v-form v-model="valid">
        <v-row>
          <v-col cols="12" md="4">
            <v-text-field
              v-model="user.userid"
              :rules="idRules"
              :counter="20"
              label="아이디"
              disabled
              required
              placeholder="아이디를 입력해주세요"
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
              :rules="[
                user.userpwd === confirmPassword || 'Password must match',
              ]"
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
              @keypress.enter="login"
              placeholder="이메일을 입력해주세요"
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
              id="userRoadAddress"
              v-model="user.address"
              label="도로명 주소"
              required
              placeholder="우편번호를 검색하면 도로명 주소가 입력됩니다"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-btn depressed color="primary" @click="updateUser">
            회원 정보 수정
          </v-btn>
          <v-space></v-space>
          <v-btn depressed color="primary" @click="deleteUser">
            회원 탈퇴
          </v-btn>
        </v-row>
      </v-form></v-card
    >
  </div>
</template>
