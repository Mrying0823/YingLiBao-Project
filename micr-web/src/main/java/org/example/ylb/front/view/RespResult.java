package org.example.ylb.front.view;

import org.example.ylb.api.pojo.product.PageInfo;
import org.example.ylb.common.enums.RespCode;

import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/9/24 1:13
 */

// 统一应答结果类，controller 返回值
public class RespResult {

    // 应答码
    private int code;

    // code 的文字说明，用于提示
    private String msg;

    // 单个数据
    private Object retData;

    // 集合数据
    private List list;

    // 分页
    private PageInfo pageInfo;

    private String accessToken;

    // 表示成功的 RespResult 对象
    public static RespResult ok() {
        RespResult respResult = new RespResult();
        respResult.setCode(RespCode.SUCCESS.getCode());
        respResult.setMsg(RespCode.SUCCESS.getMsg());
        return respResult;
    }

    // 表示失败的 RespResult 对象
    public static RespResult fail() {
        RespResult respResult = new RespResult();
        respResult.setCode(RespCode.BAD_REQUEST.getCode());
        respResult.setMsg(RespCode.BAD_REQUEST.getMsg());
        return respResult;
    }

    public static RespResult notFound() {
        RespResult respResult = new RespResult();
        respResult.setCode(RespCode.NOT_FOUND.getCode());
        respResult.setMsg(RespCode.NOT_FOUND.getMsg());
        return respResult;
    }

    public void setRespCode(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
