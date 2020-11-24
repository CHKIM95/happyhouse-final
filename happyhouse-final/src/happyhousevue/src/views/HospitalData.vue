<template>
  <v-row style="height:500px">
    <v-col cols="4" sm="4">
      <KakaoMap :propsListData="hospitalData" :mapId="hospitalMapId" />
    </v-col>
    <v-col cols="8" sm="8">
      <v-data-table
        :headers="headers"
        :items="hospitalData"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        @page-count="pageCount = $event"
        hide-default-footer
        @click:row="getDetailInfo"
      >
      </v-data-table>

      <v-pagination
        v-model="page"
        :length="pageCount"
        :total-visible="10"
        circle
      ></v-pagination>
    </v-col>
  </v-row>
</template>

<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dfebd97b0ebbc269f439edcad3f447ac&libraries=services"
></script>

<script>
import KakaoMap from '../components/KakaoMap.vue';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data: () => ({
    headers: [
      {
        text: '병원명',
        value: 'name',
      },
      { text: '시도', value: 'sido' },
      { text: '구군', value: 'gugun' },
      {
        text: '주소',
        value: `address`,
      },
      { text: '타입', value: 'type' },
      { text: '전화번호', value: 'tel' },
    ],
    hospitalData: [],
    hospitalMapId: 'hospitalMap',
    page: '',
    itemsPerPage: '5',
    pageCount: '10',
  }),

  props: ['propsGugunCode'],

  components: {
    KakaoMap,
  },

  created() {
    let _this = this;
    axios
      .get(`${SERVER_URL}/surrounding/clinic?gugun=${_this.propsGugunCode}`)
      .then((response) => {
        _this.hospitalData = response.data;
      });
  },
};
</script>
