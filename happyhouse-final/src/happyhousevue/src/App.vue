<template>
  <v-app id="inspire">
    <Header />
    <router-view
      class="wrapper"
      :inputSelectedData="selectedData"
      :selectedHouseObjArr="selectedHouseObjArr"
      @selectedData="completeSelect"
      @selectedHouseObjArr="getDetailInfo"
    ></router-view>
    <Footer />
  </v-app>
</template>

<script>
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
export default {
  data: () => ({
    selectedData: [],
    selectedHouseObjArr: [],
  }),

  components: {
    Header,
    Footer,
  },
  methods: {
    completeSelect: function(data) {
      this.selectedData = data;
      this.$router.replace('/happyhouse/houseData');
    },

    getDetailInfo: function(data) {
      this.selectedHouseObjArr = data;
      this.$router.push('/happyhouse/houseDetailData');
    },
  },
};
</script>
