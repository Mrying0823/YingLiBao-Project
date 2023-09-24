package org.example.ylb.front.view;

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
}