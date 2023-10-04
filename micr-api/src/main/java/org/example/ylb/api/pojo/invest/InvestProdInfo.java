package org.example.ylb.api.pojo.invest;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 邓和颖
 * @since 2023/10/4 8:52
 */
public class InvestProdInfo implements Serializable {

    // 投资记录的 id
    private Integer id;

    // 投资人的电话号码
    private String phone;

    // 投资金额
    private BigDecimal bidMoney;

    // 投资时间
    private String bidTime;

    public InvestProdInfo() {
    }

    public InvestProdInfo(Integer id, String phone, BigDecimal bidMoney, String bidTime) {
        this.id = id;
        this.phone = phone;
        this.bidMoney = bidMoney;
        this.bidTime = bidTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }
}
