<template>
  <v-container>
    <v-row>
      <v-col class="d-flex" cols="12" sm="3">
        <v-select
          v-model="selectedHouseType"
          :items="houseType"
          item-text="kor"
          item-value="eng"
          label="집 종류를 선택해주세요"
        ></v-select>
      </v-col>
      <v-col class="d-flex" cols="12" sm="3">
        <v-select
          v-model="selectedDealType"
          :items="dealType"
          item-text="kor"
          item-value="eng"
          label="거래 타입을 선택해주세요"
        ></v-select>
      </v-col>
      <v-col class="d-flex" cols="12" sm="3">
        <v-select
          v-model="selectedSido"
          :items="sido"
          item-text="sidoName"
          item-value="sidoCode"
          label="시도를 선택해주세요"
          @change="getGugun"
        ></v-select>
      </v-col>
      <v-col class="d-flex" cols="12" sm="3">
        <v-select
          v-model="selectedGugun"
          :items="gugun"
          item-text="gugunName"
          item-value="gugunCode"
          label="구군을 선택해주세요"
          @change="emitFunc"
        ></v-select>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data: () => ({
    sido: [],
    gugun: [],
    houseType: [
      { kor: '다세대 주택', eng: 'multiGeneration' },
      { kor: '아파트', eng: 'apartment' },
    ],
    dealType: [
      { kor: '월세', eng: 'rent' },
      { kor: '매매', eng: 'buy' },
    ],
    selectedHouseType: '',
    selectedDealType: '',
    selectedSido: '',
    selectedGugun: '',
  }),
  created() {
    // if (this.selectedGugun == '') {
    //   this.$router.replace('/');
    // }
    axios.get(`${SERVER_URL}/house/sido`).then((response) => {
      this.sido = response.data;
    });
  },
  methods: {
    getGugun: function() {
      axios
        .get(`${SERVER_URL}/house/gugun?sido=${this.selectedSido}`)
        .then((response) => {
          this.gugun = response.data;
        });
    },
    emitFunc: function() {
      this.$emit('selectedHouseType', [
        this.selectedHouseType,
        this.selectedDealType,
        this.selectedSido,
        this.selectedGugun,
      ]);
    },
  },
};
</script>
