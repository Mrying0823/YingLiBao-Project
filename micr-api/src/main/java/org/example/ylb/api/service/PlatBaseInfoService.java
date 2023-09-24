package org.example.ylb.api.service;

import org.example.ylb.api.pojo.BaseInfo;

/**
 * @author 邓和颖
 * @since 2023/9/23 20:04
 */
public interface PlatBaseInfoService {

    // 计算利率、注册人数、累计成交金额
    BaseInfo queryPlatBaseInfo();
}
