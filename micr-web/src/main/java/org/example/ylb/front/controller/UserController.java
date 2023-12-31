package org.example.ylb.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.ylb.api.model.User;
import org.example.ylb.common.constants.JwtKey;
import org.example.ylb.common.constants.RedisKey;
import org.example.ylb.common.constants.YLBConstant;
import org.example.ylb.common.utils.CommonUtils;
import org.example.ylb.front.view.RespResult;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 邓和颖
 * @since 2023/10/4 20:25
 */
@Api(tags = "用户相关")
@RequestMapping("/v1")
@RestController
public class UserController extends BaseController {

    // 验证手机号是否已注册
    @ApiOperation(value = "检验手机号是否已注册",notes = "注册用户，检验手机是否已注册")
    @ApiImplicitParam(name = "phone",value = "手机号",required = true)
    @GetMapping("/user/phoneExists")
    public RespResult phoneExists(@RequestParam("phone") String phone) {
        RespResult respResult = RespResult.fail();

        // 检验手机号格式是否正确
        if(!CommonUtils.checkPhone(phone)) {
            respResult.setMsg("手机号码格式不正确");
        }else {
            // 创建 Redis Set 实例，可执行与 set 相关的操作
            SetOperations<String,String> set = stringRedisTemplate.opsForSet();

            Set<String> userPhones = set.members(RedisKey.KEY_USER_PHONE);

            if (userPhones == null || userPhones.isEmpty()) {
                // 查询用户表中所有的电话号码
                List<String> phoneList = userService.queryUserPhone();

                // 将电话号码添加到 Redis Set 中
                set.add(RedisKey.KEY_USER_PHONE, phoneList.toArray(new String[0]));

                // 设置过期时间
                stringRedisTemplate.expire(RedisKey.KEY_USER_PHONE, 1, TimeUnit.HOURS);

                userPhones = new HashSet<>(phoneList);
            }

            // 匹配 Redis 中所有的用户电话号码，检验手机号是否已注册`
            if(userPhones.contains(phone)) {
                respResult.setMsg("手机号码已注册");
            }else {

                // 手机号未注册
                respResult = RespResult.ok();
            }
        }

        return respResult;
    }

    // 手机号注册用户
    @ApiOperation(value = "手机号注册用户",notes = "手机号注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true),
            @ApiImplicitParam(name = "code",value = "验证码",required = true)
    })
    @PostMapping("/user/register")
    public RespResult userRegister(@RequestParam("phone") String phone,
                                   @RequestParam("password") String passwd,
                                   @RequestParam("code") String code) {
        RespResult respResult = RespResult.fail();

        // 检查参数
        if(!CommonUtils.checkPhone(phone)) {
            respResult.setMsg("手机号码格式不正确");
        }else if(passwd == null || passwd.length() != 32) {

            // 检查密码
            // 密码密文应为 32 位
            respResult.setMsg("密码无效");
        }else if(!registerSmsService.checkSmsCode(phone,code)) {

            // 检查短信验证码
            respResult.setMsg("短信验证码无效");
        }else if(userService.userRegister(phone,passwd) == 1) {

            // 事务处理成功
            respResult = RespResult.ok();
        }

        return respResult;
    }

    private RespResult userLoginRespResult(User user,String phone) {

        Map<String,Object> claims = new HashMap<>();

        claims.put(JwtKey.CLAIM_KEY_PHONE,phone);

        String accessToken = jwtUtils.createToken(claims);

        // 防止用户信息暴露
        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put(YLBConstant.USER_INFO_ID,user.getId());
        userInfo.put(YLBConstant.USER_INFO_PHONE,user.getPhone());
        userInfo.put(YLBConstant.USER_INFO_NAME,user.getName());

        RespResult respResult = RespResult.ok();
        respResult.setAccessToken(accessToken);
        respResult.setRetData(userInfo);

        return respResult;
    }

    // 登录验证，获取 accessToken
    @ApiOperation(value = "用户登录",notes = "用户选择密码登录或验证码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true),
            @ApiImplicitParam(name = "password",value = "密码"),
            @ApiImplicitParam(name = "code",value = "验证码"),
            @ApiImplicitParam(name = "loginMode",value = "登录模式",required = true)
    })
    @PostMapping("/user/passwdLogin")
    public RespResult userLogin(@RequestParam("phone") String phone,
                                @RequestParam("password") String passwd,
                                @RequestParam("code") String code,
                                @RequestParam("loginMode") String loginMode
                                ) {
        RespResult respResult = RespResult.fail();

        if(!CommonUtils.checkPhone(phone)) {
            respResult.setMsg("手机号码格式不正确");

            // 密码登录
        }else if((passwd == null || passwd.length() != 32) && loginMode.equals(YLBConstant.LOGIN_BY_PASSWORD)) {
            respResult.setMsg("密码无效");

        }else if(loginMode.equals(YLBConstant.LOGIN_BY_PASSWORD)){
            User user = userService.queryUserByPhoneAndPwd(phone,passwd,YLBConstant.LOGIN_BY_PASSWORD);
            if(user != null) {
                respResult = userLoginRespResult(user,phone);
            }

            // 验证码登录
        }else if(!loginSmsService.checkSmsCode(phone,code) && loginMode.equals(YLBConstant.LOGIN_BY_CODE)) {
            respResult.setMsg("短信验证码无效");

        }else if(loginMode.equals(YLBConstant.LOGIN_BY_CODE)){
            User user = userService.queryUserByPhoneAndPwd(phone,null,YLBConstant.LOGIN_BY_CODE);
            if(user != null) {
                respResult = userLoginRespResult(user,phone);
            }
        }

        return respResult;
    }
}
