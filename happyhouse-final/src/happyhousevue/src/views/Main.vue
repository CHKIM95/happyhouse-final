<template>
  <div class="content">
    <v-container fluid>
      <v-row class="mt-16 pt-7" justify="center">
        <v-img
          position="center"
          lazy-src="@/assets/logo.png"
          max-height="400"
          max-width="400"
          src="@/assets/main.jpg"
        ></v-img>
      </v-row>
      <br />
      <SelectBox @selectedHouseType="emitFunc" />
    </v-container>
  </div>
</template>

<script>
import SelectBox from '../components/SelectBox.vue';
import Vue from 'vue';
import swal from 'vue-swal';
Vue.use(swal);
export default {
  data: () => ({
    selectedHouseType: '',
  }),

  components: {
    SelectBox,
  },
  methods: {
    emitFunc: function(data) {
      this.$emit('selectedData', data);
    },
  },
};
</script>
