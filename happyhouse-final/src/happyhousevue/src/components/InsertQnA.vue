<template>
  <div>
    <div v-if="!submitted">
      <v-card class="pa-6 mx-auto my-0" max-width="500">
        <v-card-title>게시물 등록</v-card-title>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="inputData.userid"
            label="아이디->자동입력되도록"
            required
          ></v-text-field>
          <v-text-field
            v-model="inputData.subject"
            label="제목"
            required
          ></v-text-field>

          <v-textarea
            v-model="inputData.content"
            label="내용"
            required
          ></v-textarea>

          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="insertQnA"
          >
            수정
          </v-btn>

          <v-btn color="warning" class="mr-4" @click="reset">
            초기화
          </v-btn>
          <v-btn color="error" class="mr-4" @click="showQnA">
            취소
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
  name: 'InsertQnA',
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
      inputData: {},
    };
  },
  mounted() {
    // console.log(this.no, 'is nice');
    // http
    //   .get('/qna/inputDataQnA/' + this.no)
    //   .then((response) => {
    //     this.inputData = response.data;
    //     console.log(this.inputData);
    //   })
    //   .catch(() => {
    //     this.errored = true;
    //   })
    //   .finally(() => {
    //     this.loading = false;
    //   });
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    insertQnA() {
      console.log(this.inputData.subject, '문제있나요');
      if (this.inputData.subject == '') {
        alert('제목은 필수값입니다.');
        return;
      }
      if (this.inputData.content == '') {
        alert('내용은 필수값입니다.');
        return;
      }

      http
        .post('/qna/insertQnA', {
          userid: this.inputData.userid,
          subject: this.inputData.subject,
          content: this.inputData.content,
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
