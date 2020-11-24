<template>
  <div>
    <v-container>
      <v-data-table
        :headers="headers"
        :items="propsClinicData"
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
    <v-container>
      <KakaoMap :propsListData="clinicData" :mapId="clinicMapId" />
    </v-container>
  </div>
</template>

<script>
import KakaoMap from '../components/KakaoMap.vue';

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

  props: ['propsClinicData'],

  components: {
    KakaoMap,
  },

  created() {
    this.clinicData = this.propsClinicData;
  },
};
</script>
