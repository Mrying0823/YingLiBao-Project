package org.example.ylb.dataservice.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.ylb.api.pojo.invest.InvestRankInfo;
import org.example.ylb.api.service.BidInfoService;
import org.example.ylb.dataservice.mapper.BidInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/9/27 8:16
 */
@DubboService(interfaceClass = BidInfoService.class,version = "1.0")
public class BidInfoServiceImpl implements BidInfoService {

    private BidInfoMapper bidInfoMapper;

    @Autowired
    public void setBidInfoMapper(BidInfoMapper bidInfoMapper) {
        this.bidInfoMapper = bidInfoMapper;
    }

    @Override
    public List<InvestRankInfo> getInvestRank() {
        return bidInfoMapper.selectInvestRank();
    }
}
