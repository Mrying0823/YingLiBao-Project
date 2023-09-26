package org.example.ylb.dataservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.ylb.api.model.ProdInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

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

    // 按产品类型分页查询
    List<ProdInfo> selectAllByProductTypeLimit(@Param("prodType") Integer productType,
                                               @Param("beginNo") Integer beginNo,
                                               @Param("pageSize") Integer pageSize);

    // 某个产品类型的记录总数
    Integer selectCountByProductType(@Param("prodType") Integer productType);
}