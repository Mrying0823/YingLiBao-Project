package org.example.ylb.front.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.ylb.api.service.BidInfoService;
import org.example.ylb.api.service.PlatBaseInfoService;
import org.example.ylb.api.service.ProdInfoService;
import org.example.ylb.api.service.UserService;
import org.example.ylb.front.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author 邓和颖
 * @since 2023/9/24 1:05
 */
public class BaseController {

    // 声明公共的方法、属性等

    // redis
    @Autowired
    protected StringRedisTemplate stringRedisTemplate;

    // 平台基本信息服务
    @DubboReference(interfaceClass = PlatBaseInfoService.class,version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    // 产品服务
    @DubboReference(interfaceClass = ProdInfoService.class,version = "1.0")
    protected ProdInfoService prodInfoService;

    // 投资服务
    @DubboReference(interfaceClass = BidInfoService.class,version = "1.0")
    protected BidInfoService bidInfoService;

    // 用户服务
    @DubboReference(interfaceClass = UserService.class,version = "1.0")
    protected UserService userService;

    // 短信服务
    @Autowired
    protected SmsService smsService;
}
