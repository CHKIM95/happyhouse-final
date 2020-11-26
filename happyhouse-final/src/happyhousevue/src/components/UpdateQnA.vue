<template>
  <div>
    <div v-if="!submitted">
      <v-card class="pa-6 mx-auto my-0" max-width="500">
        <v-card-title>{{ detail.no }}번 게시물을 수정합니다!</v-card-title>
        <v-form v-model="valid" lazy-validation style="text-align:center">
          <v-text-field
            v-model="detail.userid"
            label="아이디"
            disabled
          ></v-text-field>
          <v-text-field
            v-model="detail.subject"
            label="제목"
            required
            ref="formSubject"
          ></v-text-field>

          <v-textarea
            v-model="detail.content"
            label="내용"
            required
            ref="formContent"
          ></v-textarea>

          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="updateQnA"
          >
            수정하기
          </v-btn>

          <v-btn color="error" class="mr-4" @click="reset">
            초기화 하기
          </v-btn>

          <v-btn class="warning" v-on:click="showQnA">
            취소하기
          </v-btn>
        </v-form>
      </v-card>
    </div>
    <div v-else>
      <h4>성공적으로 게시물을 수정하였습니다!</h4>
      <button class="btn btn-success" v-on:click="showQnA">
        전체 목록으로 돌아가기
      </button>
    </div>
  </div>
</template>
<script>
import http from '../../http-common';

import Vue from 'vue';
import swal from 'vue-swal';
Vue.use(swal);
export default {
  name: 'updateQnA',
  props: ['no'],
  data() {
    return {
      info: null,
      loading: true,
      errored: false,
      userid: '',
      subject: '',
      content: '',
      submitted: false,
      detail: {},
    };
  },
  mounted() {
    http
      .get('/qna/detailQnA/' + this.no)
      .then((response) => {
        this.detail = response.data;
      })
      .catch(() => {
        this.errored = true;
      })
      .finally(() => {
        this.loading = false;
      });
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.formSubject.reset();
      this.$refs.formContent.reset();
    },
    updateQnA() {
      if (this.detail.subject == '' || this.detail.subject == undefined) {
        this.$swal('제목은 필수값입니다.', '제목을 입력해주세요!', 'error');
        return;
      }
      if (this.detail.content == '' || this.detail.content == undefined) {
        this.$swal('내용은 필수값입니다.', '내용을 입력해주세요!', 'error');
        return;
      }

      http
        .put('/qna/updateQnA/' + this.no, {
          subject: this.detail.subject,
          content: this.detail.content,
        })
        .then((response) => {
          if (response.data == 'success') {
            this.$router.replace('/update/success');
            this.$swal('게시물을 수정하였습니다.', '', 'success');
            // this.$router.replace('/happyhouse/qna');
            // window.location.reload();
          } else {
            this.$swal('게시물을 수정 하지 못했습니다.', '', 'error');
          }
        });
      this.submitted = true;
    },
    newQnA() {
      (this.submitted = false),
        (this.subject = ''),
        (this.content = ''),
        (this.errored = false);
    },
    showQnA() {
      http
        .get('/qna/showQnA')
        .then((response) => (this.no = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.$router.push('/happyhouse/qna');
        });
    },
  },
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
