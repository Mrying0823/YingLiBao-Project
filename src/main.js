import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'

// 引入样式
import "@/assets/css/details.css"
import "@/assets/css/list.css"
import "@/assets/css/index.css"
import "@/assets/css/login.css"
import "@/assets/css/reset.css"
import "@/assets/css/font-awesome.min.css"
import "@/assets/css/public-head.css"
import "@/assets/css/swiper.css"
import "@/assets/css/user_center.css"
import "@/assets/css/user_pay.css"
import 'element-plus/dist/index.css'

// 创建应用实例
const app = createApp(App);

// 安装 Element Plus 插件
app.use(ElementPlus, { size: 'small', zIndex: 3000 })
// 使用 Vue Router
app.use(router);
// 挂载到 DOM
app.mount('#app');
