package org.example.ylb.dataservice.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.ylb.api.model.ProdInfo;
import org.example.ylb.api.pojo.product.MultiProduct;
import org.example.ylb.api.service.ProdInfoService;
import org.example.ylb.common.constants.YLBConstant;
import org.example.ylb.common.utils.CommonUtil;
import org.example.ylb.dataservice.mapper.ProdInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/9/24 17:12
 */
@DubboService(interfaceClass = ProdInfoService.class,version = "1.0")
public class ProdInfoServiceImpl implements ProdInfoService {

    private ProdInfoMapper prodInfoMapper;

    @Autowired
    public void setProdInfoMapper(ProdInfoMapper prodInfoMapper) {
        this.prodInfoMapper = prodInfoMapper;
    }

    // 首页的三个产品数据
    @Override
    public MultiProduct queryIndexPageProducts() {

        MultiProduct multiProduct = new MultiProduct();

        // 新手宝
        List<ProdInfo> xinShouBaoList = prodInfoMapper.selectAllByProductTypeLimit(YLBConstant.PRODUCT_TYPE_XINSHOUBAO,0,1);

        // 优选产品
        List<ProdInfo> youXuanList = prodInfoMapper.selectAllByProductTypeLimit(YLBConstant.PRODUCT_TYPE_YOUXUAN,0,3);

        // 散标产品
        List<ProdInfo> sanBiaoList = prodInfoMapper.selectAllByProductTypeLimit(YLBConstant.PRODUCT_TYPE_SANBIAO,0,3);

        multiProduct.setXinShouBao(xinShouBaoList);
        multiProduct.setYouXuan(youXuanList);
        multiProduct.setSanBiao(sanBiaoList);

        return multiProduct;
    }

    // 按产品类型分页查询
    @Override
    public List<ProdInfo> queryAllByProductTypeLimit(Integer productType, Integer pageNo, Integer pageSize) {

        List<ProdInfo> prodInfoList = new ArrayList<>();
        if(productType == 0 || productType == 1 || productType == 2) {

            // 验证控制器传来的参数是否正确
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);

            Integer beginNo = (pageNo-1)*pageSize;
            prodInfoList = prodInfoMapper.selectAllByProductTypeLimit(productType, beginNo, pageSize);
        }
        return prodInfoList;
    }

    // 按产品类型查询产品记录总数
    @Override
    public Integer queryRecordNumsByType(Integer productType) {

        Integer counts = 0;
        if(productType == 0 || productType == 1 || productType == 2) {
            counts = prodInfoMapper.selectCountByProductType(productType);
        }
        return counts;
    }

    // 根据产品 id 查询产品详细信息
    @Override
    public ProdInfo queryDetailById(Integer id) {

        ProdInfo prodDetail = null;
        if(id != null && id > 0) {
            prodDetail = prodInfoMapper.selectDetailById(id);
        }
        return prodDetail;
    }
}
