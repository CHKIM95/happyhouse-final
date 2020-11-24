<template>
  <div>
    <v-row justify="center">
      <v-col cols="12" sm="6">
        <v-simple-table>
          <thead>
            <tr>
              <th colspan="2">
                상세정보
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                아파트/주택 명
              </td>
              <td>{{ aptName }}</td>
            </tr>
            <tr>
              <td>법정동</td>
              <td>{{ selectedHouseObj['법정동']._text }}</td>
            </tr>
            <tr>
              <td>지번</td>
              <td>{{ selectedHouseObj['지번']._text }}</td>
            </tr>
            <tr>
              <td>지역코드</td>
              <td>{{ selectedHouseObj['지역코드']._text }}</td>
            </tr>
            <tr>
              <td>금액</td>
              <td>{{ dealPrice }}</td>
            </tr>
            <tr>
              <td>면적</td>
              <td>{{ selectedHouseObj['전용면적']._text }}</td>
            </tr>
            <tr>
              <td>층</td>
              <td>{{ selectedHouseObj['층']._text }}</td>
            </tr>
          </tbody>
        </v-simple-table>
      </v-col>
      <v-col cols="12" sm="6">
        <!-- <KakaoMap :propsListData="clinicList" :mapId="hospitalMapId" /> -->
        그래프 자리
      </v-col>
    </v-row>
    <v-row>
      <v-btn @click="openDialog">진료소 보기</v-btn>
      <v-btn>안심병원 보기</v-btn>
    </v-row>
    <!--  -->
    <v-dialog v-model="dialog" persistent :max-width="dialogWidth">
      <v-card>
        <KakaoMap :propsListData="clinicList" :mapId="clinicMapId" />
        <v-card-title>
          <span class="headline">정보</span>
        </v-card-title>
        <v-card-text> </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from 'axios';
import KakaoMap from '../components/KakaoMap.vue';
//var convert = require('xml-js');
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data: () => ({
    hospitalMapId: 'hospitalMap',
    clinicMapId: 'clinicMap',
    aptName: '',
    dealPrice: '',
    dialog: false,
    hospitalList: [],
    clinicList: [],
    dialogWidth: '600',
  }),
  props: ['selectedHouseObj'],
  mounted() {
    if (this.selectedHouseObj['연립다세대'] == null)
      this.aptName = this.selectedHouseObj['아파트']._text;
    else this.aptName = this.selectedHouseObj['연립다세대']._text;
    if (this.selectedHouseObj['보증금액'] == null)
      this.dealPrice = this.selectedHouseObj['거래금액']._text;
    else this.dealPrice = this.selectedHouseObj['보증금액']._text;
  },

  components: {
    KakaoMap,
  },

  methods: {
    openDialog: function() {
      this.dialog = true;
    },
  },
  created() {
    let _this = this;
    axios
      .get(
        `${SERVER_URL}/surrounding/hospital?gugun=${this.selectedHouseObj['지역코드']._text}`
      )
      .then((response) => {
        _this.hospitalList = response.data;
        this.isClicked = !this.isClicked;
      });
    axios
      .get(
        `${SERVER_URL}/surrounding/clinic?gugun=${this.selectedHouseObj['지역코드']._text}`
      )
      .then((response) => {
        _this.clinicList = response.data;
        this.isClicked = !this.isClicked;
      });
  },
};
</script>
