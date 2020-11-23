<template>
  <v-app id="inspire">
    <Header />
    <router-view
      :inputSelectedData="selectedData"
      :selectedHouseObj="selectedHouseObj"
      @selectedData="completeSelect"
      @selectedHouseObj="getDetailInfo"
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
    selectedHouseObj: {},
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
      this.selectedHouseObj = data;
      this.$router.push('/happyhouse/houseDetailData');
    },
  },
};
</script>
