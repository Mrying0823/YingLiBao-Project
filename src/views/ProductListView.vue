<template>
  <div>
    <!-- 产品列表 -->

    <!--头部-->
    <page-header></page-header>

    <div class="content clearfix">
      <!--排行榜-->
      <ul class="rank-list">
        <li v-for="(item,index) in investRank" :key="item.phone">
          <img src="@/assets/image/list-rank1.png" alt="" v-if="index === 0">
          <img src="@/assets/image/list-rank2.png" alt="" v-else-if="index === 1">
          <img src="@/assets/image/list-rank3.png" alt="" v-else>
          <p class="rank-list-phone">{{ item.phone }}</p>
          <span>{{item.money}}元</span>
        </li>
      </ul>
      <!--产品列表-->
      <ul class="preferred-select clearfix">
        <li v-for="(product,index) in productList" :key="product.id">
          <h3 class="preferred-select-title">
            <span>{{product.productName}}</span>
            <img src="@/assets/image/1-bg1.jpg" alt="" v-if="index%4 === 0">
            <img src="@/assets/image/1-bg2.jpg" alt="" v-if="index%4 === 1">
            <img src="@/assets/image/1-bg3.jpg" alt="" v-if="index%4 === 2">
            <img src="@/assets/image/1-bg4.jpg" alt="" v-if="index%4 === 3">
          </h3>
          <div class="preferred-select-number">
            <p><b>{{ product.rate }}</b>%</p>
            <span>历史年化收益率</span>
          </div>
          <div class="preferred-select-date">
            <div>
              <span>投资周期</span>
              <p><b>{{product.cycle}}</b>个月</p>
            </div>
            <div>
              <span>余利可投资金额</span>
              <p><b>{{ product.leftProductMoney }}</b>元</p>
            </div>
          </div>
          <p class="preferred-select-txt">
            优选计划项目，投资回报周期{{ product.cycle }}个月，起点低，适合短期资金周转、对流动性要求高的投资人。
          </p>
          <router-link :to="{ path: '/product/productDetail', query: {productId: product.id}}" class="preferred-select-btn">立即投资</router-link>
        </li>
      </ul>

      <!--分页-->
      <div class="page_box">
        <ul class="pagination">
          <li><a href="javascript:void(0)" @click="firstPage()" style="color: red">首页</a></li>
          <li><a href="javascript:void(0)" @click="prePage()">上一页</a></li>
          <li class="active"><span>{{pageInfo.pageNo}}</span></li>
          <li><a href="javascript:void(0)" @click="nextPage()">下一页</a></li>
          <li><a href="javascript:void(0)" @click="lastPage()" style="color: red">尾页</a></li>
          <li class="totalPages"><span>共{{ pageInfo.totalPage }}页</span></li>
        </ul>
      </div>

    </div>

    <!--公共底部-->
    <page-footer></page-footer>
  </div>
</template>

<script>

import PageHeader from "@/components/PageHeader";
import PageFooter from "@/components/PageFooter";
import {toGet} from "@/axios/httpRequest";

export default {
  name: "ProductListView",
  components: {
    PageHeader,
    PageFooter
  },

  data() {
    return {
      // 投资排行榜
      investRank: [{
        phone: "",
        money: 0.00
      }],

      // 产品列表
      productList: [{
        id: 0,
        productName: "",
        rate: 0.00,
        cycle: 0,
        releaseTime: 0,
        productType: 0,
        productNo: "",
        productMoney: 0.00,
        leftProductMoney: 0.00,
        bidMinLimit: 0.00,
        bidMaxLimit: 0.00,
        productStatus: 0,
        productFullTime: "",
        productDesc: "",
        version: 0
      }],

      // 产品列表分页信息
      pageInfo: {
        pageNo: 0,
        pageSize: 0,
        totalPage: 0,
        totalRecord: 0
      }
    }
  },

  mounted() {
    // route 不是 router
    this.initPage(this.$route.query.productType);
  },

  methods: {
    // 初始化界面，导航栏跳转使用
    initPage(productType) {
      // 获取投资排行榜数据
      toGet("/v1/invest/rank").then(response => {
        if(response) {
          this.investRank = response.data.list;
        }
      });

      // 获取产品列表数据
      toGet("/v1/product/list", {productType: productType}).then(response => {
        if(response) {
          this.productList = response.data.list;
          this.pageInfo = response.data.pageInfo;
        }
      });
    },

    switchPage(pageNo,pageSize) {
      // 获取产品列表数据
      // route 不是 router
      toGet("/v1/product/list", {productType: this.$route.query.productType,pageNo: pageNo,pageSize: pageSize}).then(response => {
        if(response) {
          this.productList = response.data.list;
          this.pageInfo = response.data.pageInfo;
          this.scrollToTop();
        }
      });
    },

    // 重置滚动条
    scrollToTop() {
      this.$nextTick(() => {
        window.scrollTo(0, 0);
      });
    },

    // 首页
    firstPage() {
      if(this.pageInfo.pageNo === 1) {
        this.$message({
          type: "info",
          center: "true",
          message: "当前为第 1 页"
        });
      }else {
        this.switchPage(1,9);
      }
    },

    // 尾页
    lastPage() {
      if(this.pageInfo.pageNo === this.pageInfo.totalPage) {
        this.$message({
          type: "info",
          center: "true",
          message: "当前为最后一页"
        });
      }else {
        this.switchPage(this.pageInfo.totalPage,9);
      }
    },

    // 上一页
    prePage() {
      if(this.pageInfo.pageNo <= 1) {
        this.$message({
          type: "info",
          center: "true",
          message: "当前为第 1 页"
        });
      }else {
        this.switchPage(this.pageInfo.pageNo-1,9);
      }
    },

    // 下一页
    nextPage() {
      if(this.pageInfo.pageNo >= this.pageInfo.totalPage) {
        this.$message({
          type: "info",
          center: "true",
          message: "当前为最后一页"
        });
      }else {
        this.switchPage(this.pageInfo.pageNo+1,9);
      }
    },
  },

  // 监听 productType 的变化
  watch: {
    // 监听路由参数的变化
    "$route.query.productType": {
      // 立即触发一次
      immediate: true,
      handler(newProductType) {
        this.initPage(newProductType);
      }
    }
  }
}
</script>

<style scoped>

</style>