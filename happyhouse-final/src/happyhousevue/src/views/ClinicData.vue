<template>
  <v-row>
    <v-col cols="4" sm="4">
      <KakaoMap :propsListData="clinicData" :mapId="clinicMapId" />
    </v-col>
    <v-col cols="8" sm="8">
      <v-data-table
        :headers="headers"
        :items="clinicData"
        :page.sync="page"
        :items-per-page="itemsPerPage"
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

<script>
import KakaoMap from '../components/KakaoMap.vue';
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data: () => ({
    clinicData: [],
    headers: [
      {
        text: '진료소명',
        value: 'name',
      },
      {
        text: '주소',
        value: `address`,
      },
      { text: '검체추출', value: 'extract' },
      { text: '영업시간', value: 'weekOp' },
    ],
    clinicMapId: 'clinicMap',
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
    // _this.clinicData = this.propsClinicData;
    // alert(_this.clinicData);
    axios
      .get(`${SERVER_URL}/surrounding/clinic?gugun=${this.propsGugunCode}`)
      .then((response) => {
        _this.clinicData = response.data;
      });
  },
};
</script>
