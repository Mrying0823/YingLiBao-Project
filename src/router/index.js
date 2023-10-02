import { createRouter, createWebHistory } from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import ProductListView from "@/views/ProductListView";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView
  },
  {
    path: "/productList",
    name: "productList",
    component: ProductListView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
