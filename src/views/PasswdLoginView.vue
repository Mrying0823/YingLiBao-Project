<template>
  <!--头部-->
  <page-header></page-header>

  <div class="login-content">
    <div class="login-flex">
      <div class="login-left">
        <h3>加入动力金融网</h3>
        <p>坐享<span>{{ platInfo.historyAvgRate }}%</span>历史年化收益</p>
        <p>平台用户<span>{{ platInfo.registerUsers }}</span>位  </p>
        <p>累计成交金额<span>{{ platInfo.sumBidMoney }}</span>元</p>
      </div>
      <!---->
      <div class="login-box">
        <h3 class="login-title">欢迎登录</h3>
        <form action="" id="login_Submit">
          <div class="alert-input">
            <!--<input class="form-border user-name" name="username" type="text" placeholder="您的姓名">
            <p class="prompt_name"></p>-->
            <input type="text" class="form-border user-num" name="mobile" placeholder="请输入11位手机号" v-model="phone" @blur="checkPhone">
            <div class="err" v-show="isPhoneErrVisible">{{ phoneErr }}</div>
            <p class="prompt_num"></p>
            <input type="password" placeholder="请输入6-20位英文和数字混合密码" class="form-border user-pass" autocomplete name="password" v-model="passwd" @blur="checkPasswd">
            <div class="err" v-show="isPasswdErrVisible">{{ passwdErr }}</div>
            <p class="prompt_pass"></p>
          </div>
          <div class="alert-input-agree">
            <el-checkbox v-model="agree" />&nbsp;我已阅读并同意<a href="javascript:void(0)">《动力金融网注册服务协议》</a>
          </div>
          <el-button type="primary" class="alert-input-btn" plain :loading="isLoading" size="large" @click="loginIn">登录</el-button>
        </form>
        <div class="login-skip">
          验证码登录？ <router-link :to="{path: '/user/codeLogin'}">登录</router-link>
        </div>
      </div>
    </div>
  </div>

  <!--公共底部-->
  <page-footer></page-footer>
</template>

<script>
import PageHeader from "@/components/PageHeader";
import PageFooter from "@/components/PageFooter";
import {toGet} from "@/axios/httpRequest";

export default {
  name: "PasswdLoginView",
  components: {
    PageFooter,
    PageHeader
  },

  data() {
    return {
      platInfo: {historyAvgRate: 0.00,sumBidMoney: 0.00,registerUsers: 0},

      // 手机号
      isPhoneErrVisible: false,
      phone: "",
      phoneErr: "",

      // 密码
      isPasswdErrVisible: false,
      passwd: "",
      passwdErr: "",

      // 同意注册协议
      agree: false,

      // 登录加载
      isLoading: false,
    }
  },

  methods: {

    // 消息提示的方法
    showMessage(type, message) {
      this.$message({
        type: type,
        center: true,
        message: message
      });
    },

    // 检查手机号的方法
    checkPhone() {

      // 验证手机号格式正则表达式
      // 不需要加双引号，加双引号无法识别为正则表达式
      const phoneRegExp = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;

      if(this.phone === "" || this.phone === undefined) {
        this.phoneErr = "请输入手机号";
        this.isPhoneErrVisible = true;
      }else if(!phoneRegExp.test(this.phone)) {
        this.phoneErr = "手机号格式不正确";
        this.isPhoneErrVisible = true;
      }else {

        // 向服务器发起请求，验证手机号是否可以登录
        toGet("/v1/user/phoneExists",{phone: this.phone}).then(response => {
          if(response && response.data.code === 200) {

            // 手机号不可登录
            this.phoneErr = "该手机号未注册 ";
            this.isPhoneErrVisible = true;
          }else {

            // 手机号可登录
            this.phoneErr = "";
            this.isPhoneErrVisible = false;
          }
        });
      }
    },

    // 检查密码的方法
    checkPasswd() {

      // 检验密码长度的正则表达式
      const lengthRegExp = /^.{6,20}$/;

      //检验密码格式的正则表达式
      const passwdRegExp = /^[A-Za-z0-9]+$/;

      if(this.passwd === "" || this.passwd === undefined) {
        this.passwdErr = "请输入密码";
        this.isPasswdErrVisible = true;
      }else if(!lengthRegExp.test(this.passwd)) {
        this.passwdErr = "请输入6-20位密码";
        this.isPasswdErrVisible = true;
      }else if(!passwdRegExp.test(this.passwd)) {
        this.passwdErr = "请输入英文和数字的混合密码";
        this.isPasswdErrVisible = true;
      }else {
        this.passwdErr = "";
        this.isPasswdErrVisible = false;
      }
    },


    // 登录
    loginIn() {
      this.checkPhone();
      this.checkPasswd();

      // 等待 dom 容器更新后
      this.$nextTick(() => {
        if(this.phoneErr !== "") {
          this.showMessage("error",this.phoneErr);
        }else if(this.passwdErr !== "") {
          this.showMessage("error",this.passwdErr);
        }else if(!this.agree) {
          this.showMessage("error","请阅读并同意 《动力金融网注册服务协议》")
        }else {
          this.isLoading = true;

        }
      });
    }
  },

  mounted() {
    toGet("/v1/plat/info",{}).then(response => {
      // 如果 response 不为空
      if(response) {
        this.platInfo = response.data.retData;
      }
    });
  }
}
</script>

<style scoped>
.err {
  color: #f10c0c;
  font-size: 15px;
  width: 450px;
  margin-bottom: -19px;
  line-height: 18px;
  position: absolute;
}
</style>