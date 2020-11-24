<template>
  <v-row style="height:500px">
    <v-col cols="4" sm="4">
      <KakaoMap
        :propsListData="clinicData"
        :mapId="clinicMapId"
        @selectedEmitObjArr="getDetailInfo"
      />
    </v-col>
    <v-col cols="8" sm="8">
      <v-data-table
        :headers="headers"
        :items="clinicData"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        hide-default-footer
        @page-count="pageCount = $event"
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
          <span class="headline">정보</span>
        </v-card-title>
        <v-card-text>
          <v-simple-table>
            <tbody>
              <tr>
                <td>진료소명</td>
                <td>{{ selectedClinic['name'] }}</td>
              </tr>
              <tr>
                <td>시도</td>
                <td>{{ selectedClinic['sido'] }}</td>
              </tr>
              <tr>
                <td>구군</td>
                <td>{{ selectedClinic['gugun'] }}</td>
              </tr>
              <tr>
                <td>주소</td>
                <td>{{ selectedClinic['address'] }}</td>
              </tr>
              <tr>
                <td>검체추출 여부</td>
                <td>{{ selectedClinic['extract'] }}</td>
              </tr>
              <tr>
                <td>주중 영업 시간</td>
                <td>{{ selectedClinic['weekOp'] }}</td>
              </tr>
              <tr>
                <td>토요일 영업시간</td>
                <td>{{ selectedClinic['satOp'] }}</td>
              </tr>
              <tr>
                <td>일요일 영업시간</td>
                <td>{{ selectedClinic['sunOp'] }}</td>
              </tr>
              <tr>
                <td>전화번호</td>
                <td>{{ selectedClinic['tel'] }}</td>
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
    selectedClinic: {},
    clinicMapId: 'clinicMap',
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
    // _this.clinicData = this.propsClinicData;
    // alert(_this.clinicData);
    axios
      .get(`${SERVER_URL}/surrounding/clinic?gugun=${this.propsGugunCode}`)
      .then((response) => {
        _this.clinicData = response.data;
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
      this.selectedClinic = data[0];
      this.dialog = true;
    },
  },
};
</script>
