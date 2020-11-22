<template>
  <v-app id="inspire">
    <Header />
    <router-view
      :inputSelectedData="selectedData"
      @selectedData="completeSelect"
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
  },
};
</script>
