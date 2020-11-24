<template>
  <div>
    <SelectBox @selectedHouseType="showData" />
    <v-container>
      <v-row style="height:500px">
        <KakaoMap
          :propsListData="houseData"
          :mapId="houseMapId"
          @selectedEmitObj="getDetailInfo"
        />
      </v-row>
      <v-data-table
        :headers="headers"
        :items="houseData"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        hide-default-footer
        @click:row="getDetailInfo"
        @page-count="pageCount = $event"
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
import SelectBox from '../components/SelectBox.vue';
import KakaoMap from '../components/KakaoMap.vue';
import axios from 'axios';
import Vue from 'vue';
import VueRouter from 'vue-router';
var convert = require('xml-js');

Vue.use(VueRouter);

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
    houseMapId: 'houseMap',
    houseData: [],
    gugun: '',
    houseType: '',
    dealType: '',
    selectedHouseObj: null,
    page: '',
    itemsPerPage: '10',
    pageCount: '10',
    dialog: false,
  }),

  props: ['inputSelectedData'],

  components: {
    SelectBox,
    KakaoMap,
  },

  methods: {
    showData: function(data) {
      let _this = this;
      _this.gugun = data[3];
      _this.houseType = data[0];
      _this.dealType = data[1];
      axios
        .get(
          `${SERVER_URL}/house/searchHouse?houseType=${_this.houseType}&dealType=${_this.dealType}&gugun=${_this.gugun}`
        )
        .then((response) => {
          let json = convert.xml2json(response.data, { compact: true });
          let items = JSON.parse(json);
          _this.houseData = items.response.body.items.item;

          if (_this.houseType == 'multiGeneration')
            _this.headers[0].value = '연립다세대._text';
          else _this.headers[0].value = '아파트._text';

          if (_this.dealType == 'rent')
            _this.headers[4].value = '보증금액._text';
          else _this.headers[4].value = '거래금액._text';
        });
    },

    getDetailInfo: function(data) {
      this.$emit('selectedHouseObj', data);
    },
  },

  mounted() {
    this.showData(this.inputSelectedData);
  },
};
</script>
