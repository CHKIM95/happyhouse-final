<template>
  <div class="grey lighten-2 ">
    <v-main>
      <v-container class="py-8 px-6">
        <v-row text-align: center>
          <v-col cols="12" :justify="center">
            <v-img
              position="center"
              lazy-src="@/assets/QnABoard-banner.jpg"
              max-height="300"
              max-width="1300"
              src="@/assets/QnABoard-banner.jpg"
            ></v-img>
          </v-col>
        </v-row>
        <v-row class="mb-0 pb-0">
          <v-col class="mb-0 pb-0" cols="2">
            <v-text-field
              loader-height="0"
              dense
              :value="itemsPerPage"
              label="Items per page"
              type="number"
              min="-1"
              max="7"
              @input="itemsPerPage = parseInt($event, 10)"
            ></v-text-field>
          </v-col>
          <v-col cols="9"></v-col>
          <v-btn class="ml-4" fab small color="cyan" @click="insertQnA">
            <v-icon>
              mdi-pencil
            </v-icon>
          </v-btn>
        </v-row>
        <v-row class="mt-0" justify="center">
          <v-col class="mt-0 pt-0" cols="6">
            <v-data-table
              :headers="headers"
              :items="articles"
              :page.sync="page"
              :items-per-page="itemsPerPage"
              hide-default-footer
              class="elevation-10"
              @page-count="pageCount = $event"
              @click:row="getNum"
              v-model="detailno"
              :loading="loading"
            >
              <template slot="progress">
                <v-progress-linear
                  color="deep-purple"
                  height="10"
                  indeterminate
                ></v-progress-linear>
              </template>
            </v-data-table>
            <v-col md="32" offset-md="32">
              <div class="text-center pt-2">
                <v-pagination
                  v-model="page"
                  :length="pageCount"
                  :total-visible="10"
                  circle
                ></v-pagination>
              </div>
            </v-col>
          </v-col>
          <v-col cols="6" class="pt-0">
            <router-view :key="$route.fullPath"></router-view>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </div>
</template>

<script>
import http from '../../http-common';
export default {
  data() {
    return {
      icon: 'mdi-pencil',
      detailno: 0,
      page: 1,
      pageCount: 0,
      itemsPerPage: 7,
      headers: [
        {
          text: '글번호',
          align: 'start',
          value: 'no',
        },
        { text: '글쓴이', value: 'userid' },
        { text: '제목', value: 'subject' },
        { text: '작성 시간', value: 'regtime' },
      ],
      articles: [],
      errored: false,
    };
  },
  watch: {
    detailno: function() {
      this.$router.push('/happyhouse/qna/' + this.detailno + '/detail');
    },
  },
  methods: {
    insertQnA: function() {
      this.$router.push('/happyhouse/qna/insert');
    },
    updateQnA: function() {
      this.$router.push('/happyhouse/qna/' + this.no + '/update');
    },
    showQnA() {
      http
        .get('qna/showQnA')
        .then((response) => {
          this.articles = response.data;
          this.detailno = this.articles[0].no;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    getNum(v) {
      this.detailno = v.no;
    },
  },
  mounted() {
    this.showQnA();
  },
};
</script>
