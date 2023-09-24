package org.example.ylb.front.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.ylb.api.service.PlatBaseInfoService;

/**
 * @author 邓和颖
 * @since 2023/9/24 1:05
 */
public class BaseController {

    // 声明公共的方法、属性等
    @DubboReference(interfaceClass = PlatBaseInfoService.class,version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

}
