package org.example.ylb.front.controller;

import org.example.ylb.front.view.RespResult;
import io.swagger.annotations.Api;
import org.example.ylb.api.pojo.BaseInfo;
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
    @GetMapping("/plat/info")
    public RespResult queryPlatBaseInfo() {

        // 调用远程服务
        BaseInfo baseInfo = platBaseInfoService.queryPlatBaseInfo();

        RespResult respResult = new RespResult();
        // 表示成功
        respResult.setCode(200);
        respResult.setMsg("查询平台信息成功");
        respResult.setRetData(baseInfo);

        return respResult;
    }
}
