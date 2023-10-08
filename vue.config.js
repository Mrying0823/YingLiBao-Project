const { defineConfig } = require('@vue/cli-service')
// 头部引入
const NodePolyfillPlugin = require('node-polyfill-webpack-plugin')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: { // 环境配置
    host: '0.0.0.0',
    port: '8080',
    https: false,
  },

  configureWebpack: {
    plugins: [new NodePolyfillPlugin()],
  }
})
