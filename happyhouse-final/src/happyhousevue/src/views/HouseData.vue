<template>
  <div>
    <SelectBox @selectedHouseType="showData" />
    <v-container>
      <div id="map" style="width:80%; height:500px; margin:0 auto;"></div>
      <br />
      <v-data-table
        :headers="headers"
        :items="houseData"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        hide-default-footer
        @click:row="showDetail"
      >
      </v-data-table>

      <v-pagination
        v-model="page"
        :length="pageCount"
        :total-visible="10"
        circle
      ></v-pagination>
    </v-container>

    <!--  -->
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        fullscreen
        persistent
        transition="dialog-bottom-transition"
      >
        <v-card>
          <v-toolbar dark color="primary">
            <v-toolbar-title>상세 정보</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn icon dark @click="dialog = false">
                <v-icon>mdi-close</v-icon>
              </v-btn>
            </v-toolbar-items>
          </v-toolbar>
          <v-container>
            <v-row justify="center" v-if="selectedHouseObj !== null">
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
                    <tr v-if="!isApart">
                      <td>
                        아파트/주택 명
                      </td>
                      <td>{{ selectedHouseObj['연립다세대']._text }}</td>
                    </tr>
                    <tr v-if="isApart">
                      <td>
                        아파트/주택 명
                      </td>
                      <td>{{ selectedHouseObj['아파트']._text }}</td>
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
                    <tr v-if="!isBuy">
                      <td>금액</td>
                      <td>{{ selectedHouseObj['보증금액']._text }}</td>
                    </tr>
                    <tr v-if="isBuy">
                      <td>금액</td>
                      <td>{{ selectedHouseObj['거래금액']._text }}</td>
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
                aa
              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </v-dialog>
    </v-row>

    <!--  -->
  </div>
</template>

<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dfebd97b0ebbc269f439edcad3f447ac&libraries=services"
></script>

<script>
import SelectBox from '../components/SelectBox.vue';
import axios from 'axios';
var convert = require('xml-js');

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data: () => ({
    headers: [
      {
        text: '아파트/주택 이름',
        value: '',
      },
      {
        text: '건축년도',
        value: `건축년도._text`,
      },
      { text: '법정동', value: '법정동._text' },
      { text: '지번', value: '지번._text' },
      { text: '가격', value: '' },
      { text: '지역코드', value: '지역코드._text' },
    ],
    houseData: [],
    isApart: false,
    isBuy: false,
    selectedHouseObj: null,
    page: '',
    itemsPerPage: '10',
    pageCount: '10',
    dialog: false,
    dialogm1: '',
  }),

  props: ['inputSelectedData'],

  components: {
    SelectBox,
  },

  methods: {
    showData: function(data) {
      axios
        .get(
          `${SERVER_URL}/house/searchHouse?houseType=${data[0]}&dealType=${data[1]}&gugun=${data[3]}`
        )
        .then((response) => {
          let json = convert.xml2json(response.data, { compact: true });
          let items = JSON.parse(json);
          this.houseData = items.response.body.items.item;
          let _this = this;

          if (data[0] == 'multiGeneration') {
            this.headers[0].value = '연립다세대._text';
            this.isApart = false;
          } else {
            this.headers[0].value = '아파트._text';
            this.isApart = true;
          }
          if (data[1] == 'rent') {
            this.headers[4].value = '보증금액._text';
            this.isBuy = false;
          } else {
            this.headers[4].value = '거래금액._text';
            this.isBuy = true;
          }

          let typeOfName = '아파트';
          if (!this.isApart) typeOfName = '연립다세대';
          let typeOfDeal = '보증금액';
          if (this.isBuy) typeOfDeal = '거래금액';
          let length = _this.houseData.length;

          let mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
              center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
              level: 7, // 지도의 확대 레벨
            };

          while (mapContainer.firstChild)
            mapContainer.removeChild(mapContainer.firstChild);
          // 지도를 생성합니다
          let map = new kakao.maps.Map(mapContainer, mapOption);

          // 주소-좌표 변환 객체를 생성합니다
          let geocoder = new kakao.maps.services.Geocoder();

          _this.houseData.forEach((houseObject, index) => {
            geocoder.addressSearch(
              houseObject['법정동']._text + ' ' + houseObject['지번']._text,
              function(result, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === kakao.maps.services.Status.OK) {
                  let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                  // 결과값으로 받은 위치를 마커로 표시합니다
                  let marker = new kakao.maps.Marker({
                    map: map,
                    position: coords,
                  });

                  // 커스텀 오버레이에 표시할 컨텐츠 입니다
                  // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
                  // 별도의 이벤트 메소드를 제공하지 않습니다
                  let content =
                    '<div class="wrap">' +
                    '    <div class="info">' +
                    '        <div class="title">' +
                    houseObject[typeOfName]._text +
                    '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
                    '        </div>' +
                    '        <div class="body">' +
                    '            <div class="img">' +
                    '                <img src="" width="73" height="70">' +
                    '           </div>' +
                    '            <div class="desc">' +
                    houseObject['법정동']._text +
                    ' ' +
                    houseObject['지번']._text +
                    '                <div class="ellipsis">' +
                    '가격 : ' +
                    houseObject[typeOfDeal]._text +
                    '                <div class="jibun ellipsis">' +
                    '평수 : ' +
                    houseObject['전용면적']._text +
                    ' 층 : ' +
                    houseObject['층']._text +
                    '                <div>' +
                    '구군 코드 ' +
                    houseObject['지역코드']._text +
                    '</div>' +
                    '            </div>' +
                    '        </div>' +
                    '    </div>' +
                    '</div>';

                  // 마커 위에 커스텀오버레이를 표시합니다
                  // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
                  let overlay = new kakao.maps.CustomOverlay({
                    content: content,
                    map: map,
                    position: marker.getPosition(),
                  });

                  // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
                  kakao.maps.event.addListener(marker, 'click', function() {
                    _this.showDetail(houseObject);
                  });
                  kakao.maps.event.addListener(marker, 'mouseover', function() {
                    overlay.setMap(map);
                  });
                  kakao.maps.event.addListener(marker, 'mouseout', function() {
                    overlay.setMap(null);
                  });
                  overlay.setMap(null);
                  // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                  if (index == length - 1) map.setCenter(coords);
                } //end ok
              }
            ); //endSearch
          });

          // console.log(
          //   Object.values(this.items.response.body.items.item[0])[2]._text
          // );
        });
    },

    showDetail: function(data) {
      this.selectedHouseObj = data;
      console.log(this.selectedHouseObj);
      this.dialog = true;
    },
  },

  mounted() {
    this.showData(this.inputSelectedData);
  },
};
</script>

<style>
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
  background: white;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: '';
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png');
}
.info .link {
  color: #5085bb;
}
</style>
