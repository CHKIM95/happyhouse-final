<template>
  <div>
    <a href="https://map.kakao.com/link/map/37.402056,127.108212"> 이동 </a>
    <v-container fluid>
      <v-row justify="center">
        <v-img
          position="center"
          lazy-src="@/assets/Zip-Zung.png"
          max-height="400"
          max-width="400"
          src="@/assets/main.jpg"
        ></v-img>
      </v-row>
      <SelectBox @selectedHouseType="emitFunc" />
    </v-container>
  </div>
</template>

<script>
import SelectBox from '../components/SelectBox.vue';

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
