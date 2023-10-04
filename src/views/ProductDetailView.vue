<template>
  <!-- 产品详细信息、投资记录、我要投资 -->

  <!--头部-->
  <page-header></page-header>

  <div class="content clearfix">
    <div class="detail-left">
      <div class="detail-left-title">{{ productInfo.productName }}（{{ productInfo.productNo }}期）</div>
      <ul class="detail-left-number">
        <li>
          <span>历史年化收益率</span>
          <p><b>{{ productInfo.rate }}</b>%</p>
          <span>历史年化收益率</span>
        </li>
        <li>
          <span>募集金额（元）</span>
          <p><b>{{ productInfo.productMoney }}</b>元</p>
          <span v-if="productInfo.leftProductMoney <= 0">募集结束</span>
          <span v-else>募集中&nbsp;&nbsp;剩余募集金额{{ productInfo.leftProductMoney }}元</span>
        </li>
        <li>
          <span>投资周期</span>
          <p v-if="productInfo.productType <= 0"><b>{{ productInfo.cycle }}</b>天</p>
          <p v-else><b>{{ productInfo.cycle }}</b>个月</p>
        </li>

      </ul>
      <div class="detail-left-way">
        <span>收益获取方式</span>
        <span>收益返还：<i>到期还本付息</i></span>
      </div>
      <!--投资记录-->
      <div class="datail-record">
        <h2 class="datail-record-title">投资记录</h2>
        <div class="datail-record-list">
          <table align="center" width="880" border="0" cellspacing="0" cellpadding="0">
            <colgroup>
              <col style="width: 72px" />
              <col style="width: 203px" />
              <col style="width: 251px" />
              <col style="width: 354px" />
            </colgroup>
            <thead class="datail_thead">
            <tr>
              <th>序号</th>
              <th>投资人</th>
              <th>投资金额（元）</th>
              <th>投资时间</th>
            </tr>
            </thead>
            <tbody v-for="(info,index) in investInfoList" :key="info.id">
            <tr>
              <td>{{ index+1 }}</td>
              <td class="datail-record-phone">{{ info.phone }}</td>
              <td>{{ info.bidMoney }}</td>
              <td>{{ info.bidTime }}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>

    </div>
    <!--右侧-->
    <div class="detail-right">
      <div class="detail-right-title">立即投资</div>
      <div class="detail-right-mode">
        <h3 class="detail-right-mode-title">收益方式</h3>
        <p class="detail-right-mode-p"><span>到期还本付息</span></p>
        <h3 class="detail-right-mode-title">我的账户可用</h3>
        <div class="detail-right-mode-rmb">
          <p>资金（元）：******</p>
          <a href="login.html" target="_blank">请登录</a>
        </div>
        <h3 class="detail-right-mode-title">预计本息收入（元）</h3>
        <form action="" id="number_submit">
          <p>请在下方输入投资金额</p>
          <input type="text" placeholder="请输入日投资金额，应为100元整倍数" name="" class="number-money" >
          <input type="submit" value="立即投资" class="submit-btn" v-if="productInfo.leftProductMoney <= 0" disabled>
          <input type="submit" value="立即投资" class="submit-btn" v-else>
        </form>
      </div>
    </div>
  </div>

  <!--公共底部-->
  <page-footer></page-footer>
</template>

<script>

import PageFooter from "@/components/PageFooter";
import PageHeader from "@/components/PageHeader";
import {toGet} from "@/axios/httpRequest";

export default {
  name: "ProductDetailView",
  components: {
    PageHeader,
    PageFooter
  },

  data() {
    return {
      // 产品详细信息
      productInfo: {
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
        productFullTime: 0,
        productDesc: "",
        version: 0
      },

      // 产品投资记录
      investInfoList: [{
        id: 0,
        phone: "",
        bidMoney: 0.00,
        bidTime: ""
      }],

    }
  },

  mounted() {
    // 向服务器发起请求，获取数据，更新页面
    toGet("/v1/product/detail",{productId: this.$route.query.productId}).then(response => {
      // 如果 response 不为空
      if(response) {
        this.productInfo = response.data.retData;
        this.investInfoList = response.data.list;
      }
    });
  }
}
</script>

<style scoped>

</style>