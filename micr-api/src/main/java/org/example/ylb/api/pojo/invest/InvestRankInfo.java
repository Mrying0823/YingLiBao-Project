package org.example.ylb.api.pojo.invest;

import java.io.Serializable;

/**
 * @author 邓和颖
 * @since 2023/9/26 19:58
 */

// 存储投资排行榜的数据
public class InvestRankInfo implements Serializable {

    // 电话号码
    private String phone;

    // 投资金额
    private Double money;

    public InvestRankInfo() {
    }

    public InvestRankInfo(String phone, Double money) {
        this.phone = phone;
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
