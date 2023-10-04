import { createRouter, createWebHistory } from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import ProductListView from "@/views/ProductListView";
import ProductDetailView from "@/views/ProductDetailView";

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
  },

  {
    path: "/productDetail",
    name: "productDetail",
    component: ProductDetailView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
