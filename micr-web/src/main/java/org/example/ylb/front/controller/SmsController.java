package org.example.ylb.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

        if(CommonUtils.checkPhone(phone)) {
            if (smsService.sendSms(phone)) {
                respResult = RespResult.ok();
            }
        }

        return respResult;
    }
}
