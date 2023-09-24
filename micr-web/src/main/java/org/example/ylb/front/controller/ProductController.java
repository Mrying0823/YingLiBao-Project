package org.example.ylb.front.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.example.ylb.api.pojo.MultiProduct;
import org.example.ylb.front.view.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 邓和颖
 * @since 2023/9/24 20:59
 */

@Api(tags = "产品数据")
@RequestMapping("/v1")
@RestController
public class ProductController extends BaseController{

    @ApiOperation(value = "首页产品数据",notes = "新手宝、优选、散标")
    @GetMapping("/product/index")
    public RespResult queryIndexPageProducts() {

        RespResult respResult = RespResult.ok();

        MultiProduct multiProduct = prodInfoService.queryIndexPageProducts();

        respResult.setRetData(multiProduct);

        return respResult;
    }
}
