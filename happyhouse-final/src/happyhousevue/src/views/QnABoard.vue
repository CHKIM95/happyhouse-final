<template>
  <v-main class="brown lighten-2">
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
        <v-col cols="4"
          ><v-btn class="mx-2" fab small color="cyan" @click="insertQnA">
            <v-icon dark>
              mdi-pencil
            </v-icon>
          </v-btn>
        </v-col>
        <v-col cols="2">
          <v-text-field
            dense
            :value="itemsPerPage"
            label="Items per page"
            type="number"
            min="-1"
            max="7"
            @input="itemsPerPage = parseInt($event, 10)"
          ></v-text-field>
        </v-col>
      </v-row>
      <!-- <v-parallax src="@/assets/QnABoard-banner.jpg" height="300"></v-parallax> -->
      <v-row class="mt-0">
        <v-col cols="6">
          <v-data-table
            dark
            :headers="headers"
            :items="articles"
            :page.sync="page"
            :items-per-page="itemsPerPage"
            hide-default-footer
            class="elevation-10"
            @page-count="pageCount = $event"
            @click:row="hello"
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
        </v-col>
        <v-col cols="6">
          <router-view :key="$route.fullPath"></router-view>
          <!-- <detail></detail>
          <update></update> -->
        </v-col>
      </v-row>
      <v-row>
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
        <v-col class></v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import http from '../../http-common';
export default {
  data() {
    return {
      detailno: 0,
      page: 1,
      pageCount: 0,
      itemsPerPage: 7,
      headers: [
        {
          text: '글번호',
          align: 'start',
          // sortable: false,
          value: 'no',
        },
        { text: '글쓴이', value: 'userid' },
        { text: '제목', value: 'subject' },
        // { text: '내용', value: 'content' },
        { text: '작성 시간', value: 'regtime' },
      ],
      articles: [],
      errored: false,
    };
  },
  watch: {
    detailno: function() {
      // console.log(this.detailno);
      this.$router.push('/happyhouse/qna/' + this.detailno + '/detail');
      // console.log(this.detailno + 'fuck');
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
        .then((response) => (this.articles = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    hello(v) {
      this.detailno = v.no;
      // this.$router.push('/happyhouse/qna');
      // alert(v.no + 'hello' + this.detailno);
      // console.log('/happyhouse/qna/' + v.no + '/detail');
      // this.$router.push('/happyhouse/qna/' + v.no + '/detail');
    },
  },
  mounted() {
    this.showQnA();
  },
};
</script>
