package org.example.ylb.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.ylb.api.model.ProdInfo;
import org.example.ylb.api.pojo.invest.InvestProdInfo;
import org.example.ylb.api.pojo.product.MultiProduct;
import org.example.ylb.api.pojo.product.PageInfo;
import org.example.ylb.common.enums.RespCode;
import org.example.ylb.common.utils.CommonUtil;
import org.example.ylb.common.utils.DateUtil;
import org.example.ylb.front.view.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 邓和颖
 * @since 2023/9/24 20:59
 */

@Api(tags = "理财产品相关")
@RequestMapping("/v1")
@RestController
public class ProductController extends BaseController{

    // 首页三项产品数据
    @ApiOperation(value = "首页三类产品数据",notes = "新手宝产品、优选产品、散标产品")
    @GetMapping("/product/index")
    public RespResult queryIndexPageProducts() {

        RespResult respResult = RespResult.ok();

        MultiProduct multiProduct = prodInfoService.queryIndexPageProducts();

        respResult.setRetData(multiProduct);

        return respResult;
    }

    // 按产品类型分页查询
    @ApiOperation(value = "产品列表",notes = "根据产品类型分页查询产品记录总数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productType",value = "产品类型"),
            @ApiImplicitParam(name = "pageNo",value = "页码"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小")
    })
    @GetMapping("/product/list")
    public RespResult queryProductByProductType(@RequestParam("productType") Integer productType,
                                                @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                                                @RequestParam(value = "pageSize",required = false,defaultValue = "9") Integer pageSize) {

        RespResult respResult = RespResult.fail();

        if(productType != null && (productType == 0 || productType == 1 || productType == 2)) {

            // 验证前端传来的参数是否正确
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);

            // 根据产品类型查询产品记录总数
            Integer recordNums = prodInfoService.queryRecordNumsByType(productType);

            if(recordNums > 0) {
                // 根据产品类型查询的产品的集合
                List<ProdInfo> prodInfoList = prodInfoService.queryAllByProductTypeLimit(productType,pageNo,pageSize);

                respResult = RespResult.ok();
                // 构建 PageInfo 分页对象
                PageInfo pageInfo = new PageInfo(pageNo,pageSize,recordNums);

                respResult.setPageInfo(pageInfo);
                respResult.setList(prodInfoList);
            } else {
                respResult.setCode(RespCode.NOT_FOUND.getCode());
            }
        }

        return respResult;
    }

    // 根据产品 id 查询产品详细信息和该产品的投资记录
    @ApiOperation(value = "产品详细信息",notes = "根据产品 id 查询产品详细信息和投资记录")
    @ApiImplicitParam(name = "productId",value = "产品 Id")
    @GetMapping("/product/detail")
    public RespResult queryDetailById(@RequestParam("productId") Integer id) {
        RespResult respResult = RespResult.fail();

        ProdInfo prodDetail;
        List<InvestProdInfo> investInfoList;

        if(id != null && id > 0) {
            prodDetail = prodInfoService.queryDetailById(id);

            // 如果产品不存在，那么不应查询投资记录
            if(prodDetail != null) {
                investInfoList = bidInfoService.queryInvestInfoByProductId(id);

                investInfoList = investInfoList.stream().peek(info -> {
                    // 对手机号进行敏感处理
                    info.setPhone(CommonUtil.maskPhone(info.getPhone()));

                    // 格式化日期时间
                    info.setBidTime(DateUtil.reformatDateTime(info.getBidTime()));
                }).collect(Collectors.toList());

                respResult = RespResult.ok();
                respResult.setRetData(prodDetail);
                respResult.setList(investInfoList);
            }else {
                respResult = RespResult.notFound();
            }
        }

        return respResult;
    }
}
