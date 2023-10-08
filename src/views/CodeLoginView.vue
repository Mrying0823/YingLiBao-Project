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
      <div class="login-box">
        <h3 class="login-title">欢迎登录</h3>
        <form action="" id="login_Submit">
          <div class="alert-input">
            <input type="text" class="form-border user-num" name="mobile" placeholder="请输入11位手机号" v-model="phone" @blur="checkPhone">
            <div class="err" v-show="isPhoneErrVisible">{{ phoneErr }}</div>
            <p class="prompt_num"></p>
            <div class="form-yzm form-border">
              <input class="yzm-write" type="text" name="code" placeholder="输入短信验证码" v-model="code" @blur="checkLoginCode">
              <span class="yzm-send" type="button" id="yzmBtn" @click="getLoginCode" v-if="!isCountShow">获取验证码</span>
              <span class="yzm-send-wait" type="text" v-if="isCountShow">{{ count }}秒后重试</span>
            </div>
            <div class="err" v-show="isCodeErrVisible">{{ codeErr }}</div>
            <p class="prompt_yan"></p>
          </div>
          <div class="alert-input-agree">
            <el-checkbox v-model="agree" />&nbsp;我已阅读并同意<a href="javascript:void(0)">《动力金融网注册服务协议》</a>
          </div>
          <el-button type="primary" class="alert-input-btn" plain :loading="isLoading" size="large" @click="loginIn">登录</el-button>
        </form>
        <div class="login-skip">
          密码登录？ <router-link :to="{path: '/user/passwdLogin'}">登录</router-link>
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
  name: "CodeLoginView",
  components: {
    PageHeader,
    PageFooter
  },

  data() {
    return {
      platInfo: {historyAvgRate: 0.00,sumBidMoney: 0.00,registerUsers: 0},

      // 手机号
      isPhoneErrVisible: false,
      phone: "",
      phoneErr: "",

      // 验证码
      isCodeErrVisible: false,
      code: "",
      codeErr: "",

      // 验证码倒计时
      isCountShow: false,
      count: "",
      timer: null,

      // 同意注册协议
      agree: false,

      // 注册加载
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

    // 计时器
    timerStart() {
      // 倒计时初始值
      const TIME_COUNT = 60;

      this.count = TIME_COUNT;
      this.isCountShow = true;

      // 计时器
      this.timer = setInterval(() => {
        if(this.count > 1 && this.count <= TIME_COUNT) {
          this.count--;
        }else {
          this.isCountShow = false;
          clearInterval(this.timer);
          this.timer = null;
        }
      },1000);
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

    // 检查验证码
    checkLoginCode() {
      if (this.code === "" || this.code === undefined) {
        this.codeErr = "请输入验证码";
        this.isCodeErrVisible = true;
      } else {
        this.codeErr = "";
        this.isCodeErrVisible = false;
      }
    },

    // 发送验证码
    getLoginCode() {
      this.checkPhone();

      this.$nextTick(() => {
        // 检查手机号
        if(this.phoneErr !== "") {
          this.showMessage("error",this.phoneErr);
        }else {
          // 如何等待检验手机号已注册接口请求成功后
          // 等待 dom 容器更新后
          // 安全地访问更新后的 DOM 元素或执行其他操作
          toGet("/v1/sms/register",{phone: this.phone}).then(response => {
            if(response && response.data.code === 200) {
              this.showMessage("success","验证码发送成功");

              // 启动计时器
              this.timerStart();
            }
            else {
              this.showMessage("error",response.data.msg);

              // 启动计时器
              this.timerStart();
            }
          });
        }
      });
    },

    // 注册用户
    loginIn() {
      this.checkPhone();
      this.checkLoginCode();

      // 等待 dom 容器更新后
      this.$nextTick(() => {
        if(this.phoneErr !== "") {
          this.showMessage("error",this.phoneErr);
        }else if(this.passwdErr !== "") {
          this.showMessage("error",this.passwdErr);
        }else if(this.codeErr !== "") {
          this.showMessage("error",this.codeErr);
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