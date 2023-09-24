package org.example.ylb.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 邓和颖
 * @since 2023/9/23 20:06
 */
public class BaseInfo implements Serializable {

    // 收益率平均值
    private BigDecimal historyAvgRate;

    // 累计成交金额
    private BigDecimal sumBidMoney;

    // 注册人数
    private Integer registerUsers;

    public BaseInfo() {
    }

    public BaseInfo(BigDecimal historyAvgRate, BigDecimal sumBidMoney, Integer registerUsers) {
        this.historyAvgRate = historyAvgRate;
        this.sumBidMoney = sumBidMoney;
        this.registerUsers = registerUsers;
    }

    public BigDecimal getHistoryAvgRate() {
        return historyAvgRate;
    }

    public void setHistoryAvgRate(BigDecimal historyAvgRate) {
        this.historyAvgRate = historyAvgRate;
    }

    public BigDecimal getSumBidMoney() {
        return sumBidMoney;
    }

    public void setSumBidMoney(BigDecimal sumBidMoney) {
        this.sumBidMoney = sumBidMoney;
    }

    public Integer getRegisterUsers() {
        return registerUsers;
    }

    public void setRegisterUsers(Integer registerUsers) {
        this.registerUsers = registerUsers;
    }
}
