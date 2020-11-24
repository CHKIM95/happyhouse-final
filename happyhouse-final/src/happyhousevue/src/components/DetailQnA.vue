<template>
  <div>
    <v-card :loading="loading" class="mx-auto my-12" max-width="374">
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>

      <v-card-title>
        <h2>{{ detail.subject }}</h2>
      </v-card-title>

      <v-card-text class="py-0">
        <div class="subtitle-2">
          <b>{{ detail.userid }}</b
          >님이 올려주신 <b>{{ detail.no }}</b> 번째 글
        </div>
        <v-row align="center" class="mx-0">
          <div class="black--text m-6 my-3 font-weight-bold">
            {{ detail.content }}
          </div>
        </v-row>
        <v-card-actions>
          <v-row>
            <v-col cols="4"></v-col>
            <v-col cols="8">
              <v-btn
                color="primary"
                class="mr-2"
                outlined
                text
                @click="updateQnA"
              >
                수정하기
              </v-btn>
              <v-btn color="error" outlined text @click="deleteQnA">
                삭제하기
              </v-btn>
              <!-- <v-btn color="warning" outlined text @click="goBack">
                QnA주소로 되돌아가기
              </v-btn> -->
            </v-col>
          </v-row>
        </v-card-actions>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-card-text>
        <v-row align="center" class="mx-0 .col-md-3">
          <div class="indigo--text m-6 h6 font-weight-bold">
            게시물 평가하기
          </div>
          <v-rating
            :value="4.5"
            color="amber"
            dense
            half-increments
            size="14"
          ></v-rating>
        </v-row>
        <div class="subtitle-4">작성시간 {{ detail.regtime }}</div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import http from '../../http-common';
export default {
  name: 'detailQnA',
  props: ['no'],
  data() {
    return {
      upHere: false,
      detail: {},
      loading: true,
      errored: false,
    };
  },
  methods: {
    updateQnA: function() {
      // alert(this.no + '의 게시글를 수정할것입니다.');
      this.$router.push('/happyhouse/qna/' + this.no + '/update');
    },
    fetchData() {
      console.log('hjello');
      console.log(this.no);
      http
        .get('/qna/detailQnA/' + this.no)
        .then((response) => (this.detail = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
      console.dir(this.detail);
    },
    deleteQnA: function() {
      alert(
        this.no + '의 게시글을 삭제할것입니다. 괜찮은지는 모르겠고 지웠습니다.'
      );
      http
        .delete('/qna/deleteQnA/' + this.no)
        .then((response) => (this.detail = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
      this.$router.push('/happyhouse/qna');
      window.location.reload();
    },
    // goBack: function() {
    //   this.$router.replace('/happyhouse/qna');
    // },
  },
  watch: {
    $route: 'fecthData',
  },
  created() {
    // alert(this.no);
    this.fetchData();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
