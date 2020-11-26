<template>
  <div class="mx-auto">
    <div class="Chart">
      <InformationChart
        ref="skills_chart"
        :chart-data="chartData"
        :options="options"
      >
      </InformationChart>
    </div>
  </div>
</template>

<script>
import InformationChart from './InformationChart.vue';

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
    InformationChart,
  },
  computed: {
    currentDataSet() {
      return this.chartData.datasets[0].data;
    },
  },
  data: () => ({
    options,

    chartData: {
      labels: ['이 건물 주변 정보에요!'],
      datasets: [
        {
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
