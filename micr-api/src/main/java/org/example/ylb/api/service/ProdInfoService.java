package org.example.ylb.api.service;

import org.example.ylb.api.model.ProdInfo;
import org.example.ylb.api.pojo.product.MultiProduct;

import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/9/24 17:00
 */
public interface ProdInfoService {

    // 首个三个产品数据
    MultiProduct queryIndexPageProducts();

    // 按产品类型分页查询
    List<ProdInfo> queryAllByProductTypeLimit(Integer productType,Integer pageNo,Integer pageSize);

    // 按产品类型查询产品记录总数
    Integer queryRecordNumsByType(Integer productType);

    // 根据产品 id 查询产品详细信息
    ProdInfo queryDetailById(Integer id);
}
