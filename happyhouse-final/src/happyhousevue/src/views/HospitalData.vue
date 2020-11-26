<template>
  <v-row style="height:500px">
    <v-col cols="5" sm="5">
      <KakaoMap
        :propsListData="hospitalData"
        :mapId="hospitalMapId"
        @selectedEmitObjArr="getDetailInfo"
      />
    </v-col>
    <v-col cols="7" sm="7" class="mt-10">
      <v-data-table
        :headers="headers"
        :items="hospitalData"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        @page-count="pageCount = $event"
        hide-default-footer
        @click:row="preprocessFunc"
      >
      </v-data-table>

      <v-pagination
        v-model="page"
        :length="pageCount"
        :total-visible="10"
        circle
      ></v-pagination>
    </v-col>

    <v-dialog v-model="dialog" persistent max-width="600">
      <v-card>
        <v-card-title>
          <span class="headline">병원 정보</span>
        </v-card-title>
        <v-card-text>
          <v-simple-table>
            <tbody>
              <tr>
                <td>병원명</td>
                <td>{{ selectedHospital['name'] }}</td>
              </tr>
              <tr>
                <td>주소</td>
                <td>{{ selectedHospital['address'] }}</td>
              </tr>
              <tr>
                <td>병원 타입</td>
                <td>{{ selectedHospital['type'] }}</td>
              </tr>
              <tr>
                <td>전화번호</td>
                <td>{{ selectedHospital['tel'] }}</td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
      {
        text: '주소',
        value: `address`,
      },
      { text: '타입', value: 'type' },
      { text: '전화번호', value: 'tel' },
    ],
    selectedHospital: {},
    hospitalData: [],
    hospitalMapId: 'hospitalMap',
    page: '',
    itemsPerPage: '5',
    pageCount: '10',
    dialog: false,
  }),

  props: ['propsGugunCode'],

  components: {
    KakaoMap,
  },

  created() {
    let _this = this;
    axios
      .get(`${SERVER_URL}/surrounding/hospital?gugun=${_this.propsGugunCode}`)
      .then((response) => {
        _this.hospitalData = response.data;
      });
  },

  methods: {
    preprocessFunc: function(data) {
      let dataArr = [];
      dataArr[0] = data;
      dataArr[1] = null;
      this.getDetailInfo(dataArr);
    },
    getDetailInfo: function(data) {
      this.selectedHospital = data[0];
      this.dialog = true;
    },
  },
};
</script>
