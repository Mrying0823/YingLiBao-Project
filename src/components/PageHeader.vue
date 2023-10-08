<template>
    <!--头部-->
    <div class="public-head">
      <div class="public-head-nav">
        <div class="public-head-left">
          <h1 class="public-head-logo"><a href="javascript:">
            <img src="@/assets/image/logo.png" alt="">
          </a></h1>
          <ul class="public-head-list">
            <!-- 使用 router-link 跳转至首页 -->
            <li><router-link :to="{path: '/'}">首页</router-link></li>
            <li class="public-head-hover">
              <a href="javascript:void(0)">我要投资</a>
              <!--二级导航-->
              <div class="two-title">
                <router-link :to="{ path: '/product/productList', query: { productType: 1 } }">优选类产品</router-link>
                <router-link :to="{ path: '/product/productList', query: { productType: 2 } }">散标类产品</router-link>
              </div>
            </li>
            <li><a href="user_center.html" target="_blank">借款人信息</a></li>
            <li><a href="javascript:" target="_blank">信息披露</a></li>
            <li><a href="javascript:" target="_blank">安全计划</a></li>
          </ul>
        </div>
        <div class="public-head-right">
          <router-link :to="{path: '/user/passwdLogin'}">登录</router-link>
          <router-link :to="{path: '/user/register'}">注册</router-link>
        </div>
      </div>
    </div>
    <!--end-->
</template>

<script>

export default {
  name: "PageHeader",
}
</script>

<style scoped>

</style>