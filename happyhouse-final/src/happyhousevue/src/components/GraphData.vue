<template>
  <!-- 그래프 자리
          <button class="button" @click="addExperience">Add experience</button> -->
  <div class="mx-auto">
    <div class="Chart">
      <DoughnutExample
        ref="skills_chart"
        :chart-data="chartData"
        :options="options"
      >
      </DoughnutExample>

      <!-- <div v-for="(val, i) in currentDataSet" :key="i">
              <input
                type="range"
                min="1"
                max="8"
                placeholder="name"
                :value="currentDataSet[i]"
                @input="updateAmount($event.target.value, i)"
              />
              <span> {{ currentDataSet[i] }} years </span>
              <input
                type="text"
                :value="chartData.labels[i]"
                @input="updateName($event.target.value, i)"
              />
              <button @click="remove(i)">remove</button> -->
      <!-- </div> -->
    </div>
  </div>
</template>

<script>
import DoughnutExample from './DoughnutChart';
// import randomColor from 'randomcolor';

// chart
const options = {
  responsive: true,
  maintainAspectRatio: false,
  animation: {
    animateRotate: false,
  },
  minBarLength: 2,
  scales: {
    yAxes: [
      {
        ticks: {
          stepSize: 100,
          maxTicksLimit: 3,
        },
      },
    ],
  },
  legend: {
    display: true,
  },
};
export default {
  components: {
    DoughnutExample,
  },
  computed: {
    // chart
    currentDataSet() {
      return this.chartData.datasets[0].data;
    },
  },
  data: () => ({
    // chart
    options,
    //  {
    // maintainAspectRatio: true,
    // scales: {
    //   yAxes: [
    //     {
    //       ticks: {
    //         beginAtZero: true,
    //       },
    //     },
    //   ],
    // },
    // }

    chartData: {
      labels: ['이 건물 주변 정보에요!'],
      datasets: [
        {
          // backgroundColor: [randomColor()],
          label: ['먹거리'],
          backgroundColor: ['#B2A59F'],
          data: [2],
        },
        {
          label: ['공기'],
          data: [1],
          backgroundColor: ['#023459'],
        },
        {
          label: ['문화생활'],
          data: [2],
          backgroundColor: ['#1E646E'],
        },
        {
          label: ['안전지수'],
          data: [4],
          backgroundColor: ['#002C2F'],
        },
      ],
    },
  }),
};
</script>

<style></style>
