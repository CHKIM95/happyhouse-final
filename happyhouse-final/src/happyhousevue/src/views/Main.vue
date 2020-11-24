<template>
  <div>
    <v-container fluid>
      <v-row justify="center">
        <v-img
          position="center"
          lazy-src="@/assets/QnABoard-banner.jpg"
          max-height="600"
          max-width="600"
          src="@/assets/main.jpg"
        ></v-img>
        <SelectBox @selectedHouseType="emitFunc" />
      </v-row>
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
