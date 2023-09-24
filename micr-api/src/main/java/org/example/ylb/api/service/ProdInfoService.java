package org.example.ylb.api.service;

import org.example.ylb.api.model.ProdInfo;
import org.example.ylb.api.pojo.MultiProduct;

import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/9/24 17:00
 */
public interface ProdInfoService {

    // 按产品类型分页查询
    List<ProdInfo> queryAllByProductTypeLimit(Integer productType,Integer beginNo,Integer pageSize);

    // 首个三个产品数据
    MultiProduct queryIndexPageProducts();
}
