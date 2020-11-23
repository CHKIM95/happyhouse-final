<template>
  <div>
    <v-row justify="center">
      <v-col cols="12" sm="6">
        <v-simple-table>
          <thead>
            <tr>
              <th colspan="2">
                상세정보
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                아파트/주택 명
              </td>
              <td>{{ aptName }}</td>
            </tr>
            <tr>
              <td>법정동</td>
              <td>{{ selectedHouseObj['법정동']._text }}</td>
            </tr>
            <tr>
              <td>지번</td>
              <td>{{ selectedHouseObj['지번']._text }}</td>
            </tr>
            <tr>
              <td>지역코드</td>
              <td>{{ selectedHouseObj['지역코드']._text }}</td>
            </tr>
            <tr>
              <td>금액</td>
              <td>{{ dealPrice }}</td>
            </tr>
            <tr>
              <td>면적</td>
              <td>{{ selectedHouseObj['전용면적']._text }}</td>
            </tr>
            <tr>
              <td>층</td>
              <td>{{ selectedHouseObj['층']._text }}</td>
            </tr>
          </tbody>
        </v-simple-table>
      </v-col>
      <v-col cols="12" sm="6">
        그래프 자리
      </v-col>
    </v-row>
    <v-row>
      <v-btn @click="dialog = true">진료소 보기</v-btn>
      <v-btn>안심병원 보기</v-btn>
    </v-row>

    <!--  -->
    <v-dialog v-model="dialog" persistent fullscreen>
      <v-card>
        <v-card-title>
          <span class="headline">정보</span>
        </v-card-title>
        <v-card-text>
          정보정보
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  data: () => ({
    aptName: '',
    dealPrice: '',
    dialog: false,
  }),
  props: ['selectedHouseObj'],
  mounted() {
    if (this.selectedHouseObj['연립다세대'] == null)
      this.aptName = this.selectedHouseObj['아파트']._text;
    else this.aptName = this.selectedHouseObj['연립다세대']._text;
    if (this.selectedHouseObj['보증금액'] == null)
      this.dealPrice = this.selectedHouseObj['거래금액']._text;
    else this.dealPrice = this.selectedHouseObj['보증금액']._text;
  },
};
</script>
