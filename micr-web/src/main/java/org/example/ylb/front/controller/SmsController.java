package org.example.ylb.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.example.ylb.common.constants.RedisKey;
import org.example.ylb.common.utils.CommonUtils;
import org.example.ylb.front.view.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 邓和颖
 * @since 2023/10/5 15:24
 */
@Api(tags = "短信服务相关")
@RequestMapping("/v1")
@RestController
public class SmsController extends BaseController {

    @ApiOperation(value = "发送验证码至注册手机",notes = "注册用户，发送验证码至注册手机")
    @ApiImplicitParam(name = "phone",value = "手机号码",required = true)
    @GetMapping("/sms/register")
    public RespResult sendCodeRegister(@RequestParam("phone") String phone) {
        RespResult respResult = RespResult.fail();

        String key = RedisKey.KEY_SMS_CODE_REG+phone;

        if(!CommonUtils.checkPhone(phone)) {
            respResult.setMsg("手机号码格式不正确");
        }else if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {

            // 根据 redis 中是否存在验证码来判断是否调用第三方接口
            respResult.setMsg("验证码未过期");
        }else if (registerSmsService.sendSms(phone)) {

            // 验证码短信发送成功
            respResult = RespResult.ok();
        }

        return respResult;
    }

    @ApiOperation(value = "发送验证码至用户手机",notes = "用户登录，发送验证码至用户手机")
    @ApiImplicitParam(name = "phone",value = "手机号码",required = true)
    @GetMapping("/sms/login")
    public RespResult sendCodeLogin(@RequestParam("phone") String phone) {
        RespResult respResult = RespResult.fail();

        String key = RedisKey.KEY_SMS_CODE_LOGIN+phone;

        if(!CommonUtils.checkPhone(phone)) {
            respResult.setMsg("手机号码格式不正确");
        }else if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {

            // 根据 redis 中是否存在验证码来判断是否调用第三方接口
            respResult.setMsg("验证码未过期");
        }else if (loginSmsService.sendSms(phone)) {

            // 验证码短信发送成功
            respResult = RespResult.ok();
        }

        return respResult;
    }
}
