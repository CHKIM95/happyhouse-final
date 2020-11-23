<template>
  <div>
    <SelectBox @selectedHouseType="emitFunc" />
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
  // created() {
  //   this.$store.state.accessToken = this.$store.localStorage.accessToken;
  // },
};
</script>
