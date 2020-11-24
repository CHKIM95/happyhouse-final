<template>
  <div>
    <v-container>
      <KakaoMap :propsListData="propsClinicData" :mapId="clinicMapId" />
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

  props: ['propsClinicData'],

  components: {
    KakaoMap,
  },
};
</script>
