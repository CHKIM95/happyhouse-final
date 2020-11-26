<template>
  <div style="text-align:center">
    <div class="my-4">
      <h1>
        <i>{{ aptName }}</i>
      </h1>
    </div>
    <v-container style="width:1500px">
      <v-row class="ml-15" justify="center" style="width:1000px">
        <v-col cols="4" sm="6">
          <v-row> </v-row>
          <v-row class="mt-14">
            <v-img
              class="mx-auto"
              lazy-src="@/assets/logo.png"
              max-height="400"
              max-width="400"
              src="@/assets/house1.jpg"
            ></v-img>
          </v-row>
        </v-col>
        <v-col class="mt-2" cols="8" sm="6">
          <v-card>
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
          </v-card>
        </v-col>
      </v-row>

      <v-row justify="center" class="mt-3 mb-10">
        <v-btn @click="showKakaoMap" color="yellow" class="ml-6"
          >카카오 맵으로 보기</v-btn
        >
        <v-btn @click="showGraph" color="light-blue" class="ml-6 lighten-3"
          >건물 스펙 한눈에 보기</v-btn
        >
        <v-btn @click="showHospital" color="green" class="ml-6 lighten-3"
          >안심 병원 보기</v-btn
        >
        <v-btn @click="showClinic" color="green" class="ml-6 lighten-3"
          >진료소 보기</v-btn
        >
      </v-row>
      <v-row>
        <router-view
          :key="$route.fullPath"
          :propsGugunCode="selectedHouseObj['지역코드']._text"
        >
        </router-view>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data: () => ({
    aptName: '',
    dealPrice: '',
    dialog: false,
    hospitalList: [],
    clinicList: [],
    selectedHouseObj: {},
    coordinates: null,
    KakaoAddress: '',
  }),
  methods: {
    showGraph: function() {
      this.$router.replace('/happyhouse/houseDetailData/graphData');
    },
    showHospital: function() {
      this.$router.replace({
        name: 'HospitalData',
        params: { propsHospitalData: this.hospitalList },
      });
    },

    showKakaoMap: function() {
      window.open(this.KakaoAddress);
    },

    showClinic: function() {
      this.$router.replace('/happyhouse/houseDetailData/clinicData');
    },
  },
  computed: {},
  props: ['selectedHouseObjArr'],
  mounted() {
    this.selectedHouseObj = this.selectedHouseObjArr[0];
    this.coordinates = this.selectedHouseObjArr[1];
    this.showGraph();
    if (this.selectedHouseObj == undefined) {
      this.$router.push('/happyhouse');
    }
    if (this.selectedHouseObj['연립다세대'] == null)
      this.aptName = this.selectedHouseObj['아파트']._text;
    else this.aptName = this.selectedHouseObj['연립다세대']._text;
    if (this.selectedHouseObj['보증금액'] == null)
      this.dealPrice = this.selectedHouseObj['거래금액']._text;
    else this.dealPrice = this.selectedHouseObj['보증금액']._text;

    this.KakaoAddress = `https://map.kakao.com/link/map/${this.aptName}, ${this.coordinates[0]}, ${this.coordinates[1]}`;
  },
  created() {
    let _this = this;
    _this.selectedHouseObj = _this.selectedHouseObjArr[0];
    axios
      .get(
        `${SERVER_URL}/surrounding/hospital?gugun=${_this.selectedHouseObj['지역코드']._text}`
      )
      .then((response) => {
        _this.hospitalList = response.data;
      });
    axios
      .get(
        `${SERVER_URL}/surrounding/clinic?gugun=${_this.selectedHouseObj['지역코드']._text}`
      )
      .then((response) => {
        _this.clinicList = response.data;
      });
  },
};
</script>
