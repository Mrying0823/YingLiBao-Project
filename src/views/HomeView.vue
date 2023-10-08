<template>
  <div>
    <!--头部-->
    <page-header></page-header>

    <!--banner-->
    <div class="banner-content">
      <div class="swiper-container banner-lb">
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <a href="javascript:" >
              <img src="@/assets/image/banner.jpg" alt="">
            </a>
          </div>
          <div class="swiper-slide">
            <a href="javascript:" >
              <img src="@/assets/image/banner.jpg" alt="">
            </a>
          </div>
        </div>
      </div>
      <div class="banner-abs">
        <div class="banner-abs-box">
          <div class="banner-abs-title">动力金融网历史年化收益率</div>
          <b>{{platInfo.historyAvgRate}}<i>%</i></b>
          <p>平台用户数</p>
          <span>{{platInfo.registerUsers}}<i>位</i></span>
          <p class="banner-abs-border">累计成交金额</p>
          <span>{{platInfo.sumBidMoney}}<i>元</i></span>
        </div>
      </div>
    </div>
    <div class="banner-list">
      <ul>
        <li>
          <img src="@/assets/image/banner-tag1.png" alt="">
          <p>
            <b>实力雄厚</b>
            <span>亿级注册资本 ,千万技术投入</span>
          </p>
        </li>
        <li>
          <img src="@/assets/image/banner-tag2.png" alt="">
          <p>
            <b>风控严苛</b>
            <span>30道风控工序，60项信用审核</span>
          </p>
        </li>
        <li>
          <img src="@/assets/image/banner-tag3.png" alt="">
          <p>
            <b>投资省心</b>
            <span>资金安全风控，银行安全托管</span>
          </p>
        </li>
      </ul>
    </div>

    <!--产品-->
    <div class="content">
      <h2 class="public-title"><span>新手宝</span></h2>
      <!-- v-for 遍历 -->
      <div class="new-user" v-for="product in xinShouBao" :key="product.id">
        <div class="new-user-sm">
          <span>{{ product.bidMinLimit }}元起投</span>
          <span>投资最高限额{{ product.bidMaxLimit }}元</span>
          <span>当日即系</span>
        </div>
        <div class="new-user-number">
          <ul>
            <li>
              <p><b>{{ product.rate }}</b>%</p>
              <span>历史年化收益率</span>
            </li>
            <li>
              <p><b>{{ product.cycle }}</b>天</p>
              <span>投资周期</span>
            </li>
            <li>
              <p><b>{{ product.leftProductMoney }}</b>元</p>
              <span>余利可投资金额</span>
            </li>
          </ul>
          <router-link :to="{ path: '/product/productDetail', query: {productId: product.id}}" class="new-user-btn">立即投资</router-link>
        </div>
        <span class="new-tag">新用户专享</span>
      </div>

      <!-- 通过 url 传递参数 -->
      <h2 class="public-title"><span>优选产品</span> <router-link :to="{ path: '/product/productList', query: { productType: 1 } }" class="public-title-more">查看更多产品>></router-link></h2>
      <ul class="preferred-select clearfix">
        <li v-for="(product,index) in youXuan" :key="product.id">
          <h3 class="preferred-select-title">
            <span>{{ product.productName }}</span>
            <!-- 这里不能使用字符拼接 -->
            <img src="@/assets/image/1-bg1.jpg" alt="" v-if="index === 0">
            <img src="@/assets/image/1-bg2.jpg" alt="" v-else-if="index === 1">
            <img src="@/assets/image/1-bg3.jpg" alt="" v-else>
          </h3>
          <div class="preferred-select-number">
            <p><b>{{ product.rate }}</b>%</p>
            <span>历史年化收益率</span>
          </div>
          <div class="preferred-select-date">
            <div>
              <span>投资周期</span>
              <p><b>{{ product.cycle }}</b>个月</p>
            </div>
            <div>
              <span>余利可投资金额</span>
              <p><b>{{ product.leftProductMoney }}</b>元</p>
            </div>
          </div>
          <p class="preferred-select-txt">
            优选计划项目，投资回报周期{{product.cycle}}个月，起点低，适合短期资金周转、对流动性要求高的投资人。
          </p>
          <router-link :to="{ path: '/product/productDetail', query: {productId: product.id}}" class="preferred-select-btn">立即投资</router-link>
        </li>
      </ul>

      <h2 class="public-title"><span>散标产品</span> <router-link :to="{ path: '/product/productList', query: { productType: 2 } }" class="public-title-more">查看更多产品>></router-link></h2>
      <ul class="preferred-select clearfix">
        <li v-for="product in sanBiao" :key="product.id">
          <h3 class="preferred-select-title1">{{ product.productName }}
            <span>散标</span>
          </h3>
          <div class="preferred-select-number">
            <p><b>{{ product.rate }}</b>%</p>
            <span>历史年化收益率</span>
          </div>
          <div class="preferred-select-date">
            <div>
              <span>投资周期</span>
              <p><b>{{ product.cycle }}</b>个月</p>
            </div>
            <div>
              <span>余利可投资金额</span>
              <p><b>{{ product.leftProductMoney }}</b>元</p>
            </div>
          </div>
          <p class="preferred-select-txt">
            散标计划项目，投资回报周期{{product.cycle}}个月，起点低，适合短期资金周转、对流动性要求高的投资人。
          </p>
          <router-link :to="{ path: '/product/productDetail', query: {productId: product.id}}" class="preferred-select-btn">立即投资</router-link>
        </li>
      </ul>

    </div>

    <!--说明-->
    <div class="information-box">
      <ul>
        <li>
          <img src="@/assets/image/2-icon1.png" alt="">
          <p>优质借款</p>
          <span>严苛风控，多重审核</span>

        </li>
        <li>
          <img src="@/assets/image/2-icon2.png" alt="">
          <p>次日计息</p>
          <span>闪电成交，谁比我快</span>
        </li>
        <li>
          <img src="@/assets/image/2-icon3.png" alt="">
          <p>全年无休</p>
          <span>百万用户，一路同行</span>
        </li>
        <li>
          <img src="@/assets/image/2-icon4.png" alt="">
          <p>知心托付</p>
          <span>百万用户，一路同行</span>
        </li>
        <li>
          <img src="@/assets/image/2-icon5.png" alt="">
          <p>技术保障</p>
          <span>千万投入，专注研发</span>
        </li>
      </ul>
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
  name: 'HomeView',
  components: {
    PageHeader,
    PageFooter,
  },
  data() {
    // 返回一个包含数据属性的对象，这些属性将在组件的实例化过程中添加到组件实例中
    return {
      platInfo: {historyAvgRate: 0.00,sumBidMoney: 0.00,registerUsers: 0},
      xinShouBao: [{
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
      youXuan: [{
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
      sanBiao: [{
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
      }]
    }
  },
  mounted() {
    // 页面加载至浏览器，执行 mounted
    // 向服务器发起请求，获取数据，更新页面
    toGet("/v1/plat/info",{}).then(response => {
      // 如果 response 不为空
      if(response) {
        this.platInfo = response.data.retData;
      }
    });

    toGet("/v1/product/index",{}).then(response => {
      // 如果 response 不为空
      if(response) {
        this.xinShouBao = response.data.retData.xinShouBao;
        this.youXuan = response.data.retData.youXuan;
        this.sanBiao = response.data.retData.sanBiao;
      }
    });
  }
}
</script>
