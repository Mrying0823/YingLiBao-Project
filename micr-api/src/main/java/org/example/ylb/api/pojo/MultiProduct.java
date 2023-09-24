package org.example.ylb.api.pojo;

import org.example.ylb.api.model.ProdInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/9/24 20:23
 */

// 三个产品的数据
public class MultiProduct implements Serializable {
    private List<ProdInfo> xinShouBao;
    private List<ProdInfo> youXuan;
    private List<ProdInfo> sanBiao;

    public List<ProdInfo> getXinShouBao() {
        return xinShouBao;
    }

    public void setXinShouBao(List<ProdInfo> xinShouBao) {
        this.xinShouBao = xinShouBao;
    }

    public List<ProdInfo> getYouXuan() {
        return youXuan;
    }

    public void setYouXuan(List<ProdInfo> youXuan) {
        this.youXuan = youXuan;
    }

    public List<ProdInfo> getSanBiao() {
        return sanBiao;
    }

    public void setSanBiao(List<ProdInfo> sanBiao) {
        this.sanBiao = sanBiao;
    }
}
