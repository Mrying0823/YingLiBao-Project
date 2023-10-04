package org.example.ylb.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.ylb.api.pojo.plat.BaseInfo;
import org.example.ylb.front.view.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 邓和颖
 * @since 2023/9/24 1:03
 */

@Api(tags = "平台基本信息")
@RestController
@RequestMapping("/v1")
public class PlatInfoController extends BaseController {

    // 平台基本信息
    @ApiOperation(value = "平台基本信息",notes = "注册人数、平均利率、累计成交金额")
    @GetMapping("/plat/info")
    public RespResult queryPlatBaseInfo() {

        // 调用远程服务
        BaseInfo baseInfo = platBaseInfoService.queryPlatBaseInfo();

        RespResult respResult = RespResult.ok();

        respResult.setRetData(baseInfo);

        return respResult;
    }
}
