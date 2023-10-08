import { createRouter, createWebHistory } from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import ProductListView from "@/views/ProductListView";
import ProductDetailView from "@/views/ProductDetailView";
import RegisterView from "@/views/RegisterView";
import LoginView from "@/views/LoginView";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView
  },

  {
    path: "/product/productList",
    name: "productList",
    component: ProductListView
  },

  {
    path: "/product/productDetail",
    name: "productDetail",
    component: ProductDetailView
  },

  {
    path: "/user/register",
    name: "register",
    component: RegisterView
  },

  {
    path: "/user/login",
    name: "login",
    component: LoginView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
