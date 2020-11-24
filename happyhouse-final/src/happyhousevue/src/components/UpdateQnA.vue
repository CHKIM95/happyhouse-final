<template>
  <div>
    <div v-if="!submitted">
      <v-card class="pa-6 mx-auto my-0" max-width="500">
        <v-card-title>{{ detail.no }}번 게시물을 수정합니다!</v-card-title>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="detail.userid"
            label="아이디"
            disabled
          ></v-text-field>
          <v-text-field
            v-model="detail.subject"
            label="제목"
            required
          ></v-text-field>

          <v-textarea
            v-model="detail.content"
            label="내용"
            required
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

          <v-btn color="warning" @click="resetValidation">
            Reset Validation
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
    console.log(this.no, 'is nice');
    http
      .get('/qna/detailQnA/' + this.no)
      .then((response) => {
        this.detail = response.data;
        console.log(this.detail);
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
      this.$refs.form.reset();
    },
    updateQnA() {
      console.log(this.detail.subject, '문제있나요');
      if (this.detail.subject == '') {
        alert('제목은 필수값입니다.');
        return;
      }
      if (this.detail.content == '') {
        alert('내용은 필수값입니다.');
        return;
      }

      http
        .put('/qna/updateQnA/' + this.no, {
          subject: this.detail.subject,
          content: this.detail.content,
        })
        .then((response) => {
          if (response.data == 'success') {
            alert('게시물을 등록하였습니다.');
            this.$router.replace('/happyhouse/qna');
            window.location.reload();
          } else {
            alert('게시물을 등록 하지 못했습니다.');
          }
        });
      this.submitted = true;
      // this.showQnA();
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
