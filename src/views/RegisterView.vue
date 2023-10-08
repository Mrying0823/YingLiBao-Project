<template>
  <!--头部-->
  <page-header></page-header>

  <div class="login-content">
    <div class="login-flex">
      <div class="login-left">
        <p>万民用户知心托付&nbsp;&nbsp;&nbsp;&nbsp;<span>{{ historyAvgRate }}%</span>历史年化收益</p>
        <p>千万级技术研发投入&nbsp;&nbsp;&nbsp;&nbsp;亿级注册资本平台  </p>
      </div>
      <div class="login-box">
        <h3 class="login-title">用户注册</h3>
        <form action="" id="register_Submit">
          <div class="alert-input">
            <!-- @blur 添加失焦事件 -->
            <input type="text" class="form-border user-num" name="mobile" placeholder="请输入11位手机号" v-model="phone" @blur="checkPhone">
            <div class="err" v-show="isPhoneErrVisible">{{ phoneErr }}</div>
            <p class="prompt_num"></p>
            <input type="password" placeholder="请输入6-20位英文和数字混合密码" class="form-border user-pass" autocomplete name="password" v-model="passwd" @blur="checkPasswd">
            <div class="err" v-show="isPasswdErrVisible">{{ passwdErr }}</div>
            <p class="prompt_pass"></p>
            <div class="form-yzm form-border">
              <input class="yzm-write" type="text" name="code" placeholder="输入短信验证码" v-model="code" @blur="checkRegisterCode">
              <span class="yzm-send" type="button" id="yzmBtn" @click="getRegisterCode" v-if="!isCountShow">获取验证码</span>
              <span class="yzm-send-wait" type="text" v-if="isCountShow">{{ count }}秒后重试</span>
            </div>
            <div class="err" v-show="isCodeErrVisible">{{ codeErr }}</div>
            <p class="prompt_yan"></p>
          </div>
          <div class="alert-input-agree">
            <el-checkbox v-model="agree" />&nbsp;我已阅读并同意<a href="javascript:void(0)">《动力金融网注册服务协议》</a>
          </div>
          <!-- :loading 语法将其绑定到组件的 loading 属性上 -->
          <el-button type="primary" class="alert-input-btn" plain :loading="isLoading" size="large" @click="registerUser">注册</el-button>
        </form>
        <div class="login-skip">
          已有账号？ <router-link :to="{path: '/user/login'}">登录</router-link>
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
import {doPost, toGet} from "@/axios/httpRequest";
import md5 from "js-md5";

export default {
  name: "RegisterView",
  components: {
    PageHeader,
    PageFooter
  },

  data() {
    return {
      historyAvgRate: 0.00,

      // 手机号
      isPhoneErrVisible: false,
      phone: "",
      phoneErr: "",

      // 密码
      isPasswdErrVisible: false,
      passwd: "",
      passwdErr: "",

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

        // 向服务器发起请求，验证手机号是否可以注册
        toGet("/v1/user/phoneExists",{phone: this.phone}).then(response => {
          if(response && response.data.code === 200) {

            // 手机号可以注册
            this.phoneErr = "";
            this.isPhoneErrVisible = false;
          }else {

            // 手机号不可注册
            this.phoneErr = response.data.msg;
            this.isPhoneErrVisible = true;
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

    // 检查验证码
    checkRegisterCode() {
      if (this.code === "" || this.code === undefined) {
        this.codeErr = "请输入验证码";
        this.isCodeErrVisible = true;
      } else {
        this.codeErr = "";
        this.isCodeErrVisible = false;
      }
    },

    // 发送验证码
    getRegisterCode() {
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
    registerUser() {
      this.checkPhone();
      this.checkPasswd();
      this.checkRegisterCode();

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

          // 数据正确，向后端发起注册请求
          // 使用 MD5 加密密码
          let newPasswd = md5(this.passwd);

          // post 请求方法，url 是请求的地址，params 是请求的 json 对象
          // 前端传递的参数后端要能接收到
          // 把 json 对象转为 QS 格式
          // "a=2&b=3" 这种数据传输格式通常被称为 URL 编码或查询字符串（Query String）
          doPost("/v1/user/register",{
            phone: this.phone,
            password: newPasswd,
            code: this.code
          }).then(response => {
            if(response && response.data.code === 200) {
              this.showMessage("success","注册成功，自动跳转登录界面");
              this.isLoading = false;
              this.$router.push({
                path: "/user/login"
              });
            }else {
              this.showMessage("error",response.data.msg);
              this.isLoading = false;
            }
          });
        }
      });
    }
  },

  mounted() {
    // 页面加载至浏览器，执行 mounted
    // 向服务器发起请求，获取数据，更新页面
    toGet("/v1/plat/info",{}).then(response => {
      // 如果 response 不为空
      if(response) {
        this.historyAvgRate = response.data.retData.historyAvgRate;
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