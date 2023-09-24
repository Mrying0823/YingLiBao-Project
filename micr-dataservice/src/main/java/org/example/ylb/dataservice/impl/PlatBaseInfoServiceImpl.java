package org.example.ylb.dataservice.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.ylb.api.pojo.BaseInfo;
import org.example.ylb.api.service.PlatBaseInfoService;
import org.example.ylb.dataservice.mapper.BidInfoMapper;
import org.example.ylb.dataservice.mapper.ProdInfoMapper;
import org.example.ylb.dataservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author 邓和颖
 * @since 2023/9/23 20:28
 */

@DubboService(interfaceClass = PlatBaseInfoService.class,version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {

    // 注入 Mapper
    private UserMapper userMapper;

    private ProdInfoMapper prodInfoMapper;

    private BidInfoMapper bidInfoMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setProdInfoMapper(ProdInfoMapper prodInfoMapper) {
        this.prodInfoMapper = prodInfoMapper;
    }

    @Autowired
    public void setBidInfoMapper(BidInfoMapper bidInfoMapper) {
        this.bidInfoMapper = bidInfoMapper;
    }

    // 平台基本信息
    // 第一个接口功能实现
    @Override
    public BaseInfo queryPlatBaseInfo() {
        // 获取注册人数、收益率平均值、累计成交金额
        Integer registerUsers = userMapper.selectCountUser();
        BigDecimal historyAvgRate = prodInfoMapper.selectAvgRate();
        BigDecimal sumBidMoney = bidInfoMapper.selectSumBidMoney();

        return new BaseInfo(historyAvgRate,sumBidMoney,registerUsers);
    }
}
