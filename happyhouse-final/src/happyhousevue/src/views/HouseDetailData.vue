<template>
  <div style="text-align:center">
    <div class="my-4">
      <h1>
        <i>{{ aptName }}</i>
      </h1>
    </div>
    <v-container>
      <v-row justify="center">
        <v-col cols="4" sm="6">
          <v-row> </v-row>
          <v-row>
            <v-img
              position="center"
              lazy-src="@/assets/Zip-Zung.png"
              max-height="400"
              max-width="400"
              src="https://source.unsplash.com/400x400/?house"
            ></v-img>
          </v-row>
        </v-col>
        <v-col cols="8" sm="6">
          <v-simple-table dense>
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
      </v-row>

      <!--       
      <v-dialog v-model="dialog" persistent fullscreen>
        <v-card>
          <v-card-title>
            <span class="headline">정보</span>
          </v-card-title>
          <v-card-text>
            정보정보
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog> -->
      <v-row>
        <h3>
          <i>우리 주변 코로나 정보</i>
        </h3>
      </v-row>

      <v-row justify="space-around" class="mt-3 mb-10">
        <v-btn @click="showKakaoMap">카카오 맵으로 보기</v-btn>
        <v-btn @click="showGraph">건물 스펙 한눈에 보기</v-btn>
        <v-btn @click="showHospital">안심 병원 보기</v-btn>
        <v-btn @click="showClinic">진료소 보기</v-btn>
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
      this.$router.push('/happyhouse/houseDetailData/graphData');
    },
    showHospital: function() {
      // console.log(this.hospitalList);
      // let propsHospitalData = this.hospitalList;
      // this.$router.push(
      //   '/happyhouse/houseDetailData/hospitalData',
      //   propsHospitalData
      // );
      this.$router.push({
        name: 'HospitalData',
        params: { propsHospitalData: this.hospitalList },
      });
      // this.$router.push('/happyhouse/houseDetailData/hospitalData');
    },

    showKakaoMap: function() {
      // console.log(this.hospitalList);
      // let propsHospitalData = this.hospitalList;
      // this.$router.push(
      //   '/happyhouse/houseDetailData/hospitalData',
      //   propsHospitalData
      // );
      // this.$router.push({
      //   name: 'HospitalData',
      //   params: { propsHospitalData: this.hospitalList },
      // });
      //"_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400"
      window.open(this.KakaoAddress);
    },

    showClinic: function() {
      // let propsClinicData = this.clinicList;
      console.log(this.clinicList);
      // this.$router.push(
      //   '/happyhouse/houseDetailData/clinicData',
      //   propsClinicData
      // );
      // this.$router.push({
      //   name: 'ClinicData',
      //   params: { propsClinicData: this.clinicList },
      // });
      this.$router.push('/happyhouse/houseDetailData/clinicData');
    },

    // chart
    // updateChart() {
    //   this.$refs.skills_chart.update();
    // },
    // updateAmount(amount, index) {
    //   this.chartData.datasets[0].data.splice(index, 1, amount);
    //   this.updateChart();
    // },
    // updateName(text, index) {
    //   this.chartData.labels.splice(index, 1, text);
    //   this.updateChart();
    // },
    // addExperience() {
    //   var currentDataset = this.chartData.datasets[0];
    //   this.chartData.labels.push(`Skill ${currentDataset.data.length + 1}`);
    //   currentDataset.data.push(1);
    //   // currentDataset.backgroundColor.push(randomColor());
    //   currentDataset.backgroundColor.push('blue');
    //   this.updateChart();
    // },
    // remove(index) {
    //   const currentDataset = this.chartData.datasets[0];
    //   currentDataset.data.splice(index, 1);
    //   this.chartData.labels.splice(index, 1);
    //   currentDataset.backgroundColor.splice(index, 1);
    //   this.updateChart();
    // },
  },
  computed: {},
  props: ['selectedHouseObjArr'],
  mounted() {
    this.selectedHouseObj = this.selectedHouseObjArr[0];
    this.coordinates = this.selectedHouseObjArr[1];

    if (
      (((this.selectedHouseObj['연립다세대'] == undefined &&
        this.selectedHouseObj['아파트']) == undefined &&
        this.selectedHouseObj['보증금액']) == undefined &&
        this.selectedHouseObj['거래금액']) == undefined
    ) {
      this.$router.push('/');
    }
    if (this.selectedHouseObj['연립다세대'] == null)
      this.aptName = this.selectedHouseObj['아파트']._text;
    else this.aptName = this.selectedHouseObj['연립다세대']._text;
    if (this.selectedHouseObj['보증금액'] == null)
      this.dealPrice = this.selectedHouseObj['거래금액']._text;
    else this.dealPrice = this.selectedHouseObj['보증금액']._text;

    this.KakaoAddress = `https://map.kakao.com/link/map/${this.aptName}, ${this.coordinates[0]}, ${this.coordinates[1]}`;
    alert(this.KakaoAddress);
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
        console.log(_this.hospitalList);
        // this.isClicked = !this.isClicked;
      });
    axios
      .get(
        `${SERVER_URL}/surrounding/clinic?gugun=${_this.selectedHouseObj['지역코드']._text}`
      )
      .then((response) => {
        _this.clinicList = response.data;
        console.log(_this.clinicList);
        // this.isClicked = !this.isClicked;
      });
  },
};
</script>
