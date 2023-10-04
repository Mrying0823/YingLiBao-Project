package org.example.ylb.api.service;

import org.example.ylb.api.pojo.invest.InvestProdInfo;
import org.example.ylb.api.pojo.invest.InvestRankInfo;

import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/9/27 8:14
 */
public interface BidInfoService {

    // 投资排行榜
    List<InvestRankInfo> getInvestRank();

    // 某个产品的投资记录
    List<InvestProdInfo> queryInvestInfoByProductId(Integer productId);
}
