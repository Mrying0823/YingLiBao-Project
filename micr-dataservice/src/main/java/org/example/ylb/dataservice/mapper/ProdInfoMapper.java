package org.example.ylb.dataservice.mapper;

import org.example.ylb.api.model.ProdInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author 邓和颖
 */
@Repository
public interface ProdInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProdInfo record);

    int insertSelective(ProdInfo record);

    ProdInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProdInfo record);

    int updateByPrimaryKey(ProdInfo record);

    // 利率平均值
    BigDecimal selectAvgRate();
}