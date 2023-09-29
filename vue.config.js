const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: { // 环境配置
    host: '0.0.0.0',
    port: '8080',
    https: false,
  }
})
