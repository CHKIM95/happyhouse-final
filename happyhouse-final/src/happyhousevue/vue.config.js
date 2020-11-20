module.exports = {
  outputDir: './dist',
  indexPath: './index.html',
  devServer: {
    proxy: 'http://localhost:8000/happyhouse',
  },
  chainWebpack: (config) => {
    const svgRule = config.module.rule('svg');
    svgRule.uses.clear();
    svgRule.use('vue-svg-loader').loader('vue-svg-loader');
  },
};
