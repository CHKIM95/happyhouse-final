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

      <v-card-title class="subtitle-2">
        <h2>{{ detail.subject }}</h2>
      </v-card-title>

      <v-card-text class="py-0">
        <div class="subtitle-2">
          작성자 : <b>{{ detail.userid }}</b>
        </div>
        <v-row align="center" class="mx-0">
          <div class="black--text m-6 my-3 ">
            {{ detail.content }}
          </div>
        </v-row>
        <v-card-actions>
          <v-row v-if="detail.userid == getUserId">
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
            </v-col>
          </v-row>
        </v-card-actions>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-card-text>
        <v-row align="center" class="mx-0 .col-md-3">
          <a class="indigo--text m-6 h6 font-weight-bold">
            게시물 평가하기
          </a>
          <v-rating
            :value="4.5"
            color="amber"
            dense
            half-increments
            size="14"
          ></v-rating>
        </v-row>
        <div class="subtitle-4">
          #<b>{{ detail.no }}</b
          >, 작성시간 {{ detail.regtime }}
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import http from '../../http-common';
import Vue from 'vue';
import swal from 'vue-swal';
Vue.use(swal);

import { mapGetters } from 'vuex';
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
      this.$router.push('/happyhouse/qna/' + this.no + '/update');
    },
    fetchData() {
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
      http
        .delete('/qna/deleteQnA/' + this.no)
        .then((response) => (this.detail = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
      this.$swal(this.no + '번글을 삭제했어요', '', 'success');
      this.$router.push('/delete/success');
    },
  },
  watch: {
    $route: 'fecthData',
  },
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserId']),
  },
  created() {
    this.fetchData();
  },
};
</script>
