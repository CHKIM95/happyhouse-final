<template>
  <v-app id="inspire">
    <Header />
    <router-view></router-view>
    <Footer />
  </v-app>
</template>

<script>
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';

export default {
  data: () => ({
    links: ['Dashboard', 'Messages', 'Profile', 'Updates'],
  }),
  components: {
    Header,
    Footer,
  },
};
</script>
