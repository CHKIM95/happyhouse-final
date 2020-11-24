<template>
  <v-footer color="#4181A6" dark padless>
    <v-card color="#4181A6" flat tile class="lighten-1 white--text text-center">
      <v-card-text>
        <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="white--text pt-0">
        안녕하세요. accumsan id ultrices nunc. Sed at orci sed massa consectetur
        dignissim a sit amet dui. Duis commodo vitae velit et faucibus. Morbi
        vehicula lacinia malesuada. Nulla placerat augue vel ipsum ultrices,
        cursus iaculis dui sollicitudin. Vestibulum eu ipsum vel diam elementum
        tempor vel ut or
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        {{ new Date().getFullYear() }} —
        <strong>김창현 서범석 "All rights reserved"</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    icons: ['mdi-facebook', 'mdi-twitter', 'mdi-linkedin', 'mdi-instagram'],
  }),
};
</script>
