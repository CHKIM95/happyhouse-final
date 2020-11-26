<template>
  <div>
    <div v-if="!submitted">
      <v-card class="pa-6 mx-auto my-12" max-width="500">
        <v-card-title>게시물 등록</v-card-title>
        <v-form v-model="valid" lazy-validation style="text-align:center">
          <v-text-field
            v-model="inputData.userid"
            label="아이디"
            disabled
            required
          ></v-text-field>
          <v-text-field
            v-model="inputData.subject"
            label="제목"
            ref="formSubject"
            required
          ></v-text-field>

          <v-textarea
            v-model="inputData.content"
            label="내용"
            ref="formContent"
            required
          ></v-textarea>

          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="insertQnA"
          >
            등록
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
    <div v-else class="content">
      <v-card :loading="loading" class="mx-auto" max-width="500">
        <template slot="progress">
          <v-progress-linear
            color="#4181a6"
            height="10"
            indeterminate
          ></v-progress-linear>
        </template>

        <v-card-title>
          {{ this.resultString }}
        </v-card-title>

        <v-card-text class="py-0 wrapper">
          <v-card-actions class="mx-auto">
            <v-btn
              color="#4181a6"
              style="color:white; margin-left:25%"
              @click="showQnA"
            >
              Go back to QnA List
            </v-btn>
          </v-card-actions>
        </v-card-text>
      </v-card>
    </div>
  </div>
</template>
<script>
import http from '../../http-common';
import Vue from 'vue';
import swal from 'vue-swal';
Vue.use(swal);
import { mapGetters } from 'vuex';
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
      resultString: '',
    };
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.formSubject.reset();
      this.$refs.formContent.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    insertQnA() {
      if (this.inputData.subject == undefined || this.inputData.subject == '') {
        this.$swal('제목이 비어있어요', '제목은 필수에요', 'error');
        return;
      }
      if (this.inputData.content == undefined || this.inputData.content == '') {
        this.$swal('내용이 비어있어요', '내용은 필수에요', 'error');
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
            this.$swal('축하해요!!', 'QnA가 등록되었어요', 'success');
            this.resultString = '멋진 질문이 등록 되었는지 확인해볼까요?';
          } else {
            this.$swal('아쉽네요..', 'QnA가 등록되지 않았어요', 'return');
            this.resultString = '아쉽지만 다시 시도해주세요!';
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
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserId']),
  },
  created() {
    this.inputData.userid = this.getUserId;
  },
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
