package org.example.ylb.dataservice.mapper;

import org.example.ylb.api.model.ProdInfo;

public interface ProdInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProdInfo record);

    int insertSelective(ProdInfo record);

    ProdInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProdInfo record);

    int updateByPrimaryKey(ProdInfo record);
}