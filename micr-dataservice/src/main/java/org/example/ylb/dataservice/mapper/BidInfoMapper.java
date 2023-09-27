package org.example.ylb.dataservice.mapper;

import org.example.ylb.api.model.BidInfo;
import org.example.ylb.api.pojo.invest.InvestRankInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 邓和颖
 */
@Repository
public interface BidInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    // 累计成交金额
    BigDecimal selectSumBidMoney();

    // 投资排行榜
    List<InvestRankInfo> selectInvestRank();
}