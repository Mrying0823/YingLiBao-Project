package org.example.ylb.dataservice.mapper;

import org.example.ylb.api.model.FinanceAccount;
import org.springframework.stereotype.Repository;

/**
 * @author 邓和颖
 */
@Repository
public interface FinanceAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceAccount record);

    int insertSelective(FinanceAccount record);

    FinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceAccount record);

    int updateByPrimaryKey(FinanceAccount record);

    // 开通用户资金账户
    int insertNewFinanceAccount(FinanceAccount financeAccount);
}