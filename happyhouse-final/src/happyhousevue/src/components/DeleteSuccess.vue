<template>
  <div class="content mt-15">
    <v-card :loading="loading" class="mt-15 pa-5 mx-auto" max-width="500">
      <template slot="progress">
        <v-progress-linear
          color="#4181a6"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>

      <v-card-title>
        <h3>
          게시물을 삭제했어요!!<br />
          돌아가서 확인해보세요!!!
        </h3>
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
</template>

<script>
import http from '../../http-common';
export default {
  methods: {
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

<style></style>
