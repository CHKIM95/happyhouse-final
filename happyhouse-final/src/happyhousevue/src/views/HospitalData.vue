<template>
  <div>
    <v-container>
      <KakaoMap :propsListData="propsHospitalData" :mapId="hospitalMapId" />
    </v-container>
    <v-container>
      <v-data-table
        :headers="headers"
        :items="propsHospitalData"
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
    </v-container>
  </div>
</template>

<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dfebd97b0ebbc269f439edcad3f447ac&libraries=services"
></script>

<script>
import KakaoMap from '../components/KakaoMap.vue';

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
    hospitalMapId: 'hospitalMap',
    page: '',
    itemsPerPage: '5',
    pageCount: '10',
  }),

  props: ['propsHospitalData'],

  components: {
    KakaoMap,
  },
};
</script>
