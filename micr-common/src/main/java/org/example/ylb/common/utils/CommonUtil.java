package org.example.ylb.common.utils;

import java.util.regex.Pattern;

/**
 * @author 邓和颖
 * @since 2023/9/24 19:58
 */
public class CommonUtil {

    // 处理 pageNo
    public static int defaultPageNo(Integer pageNo) {
        return (pageNo == null || pageNo < 1) ? 1 : pageNo;
    }

    // 处理 pageSize
    public static int defaultPageSize(Integer pageSize) {
        return (pageSize == null || pageSize < 1) ? 1 : pageSize;
    }

    // 手机号敏感处理
    public static String maskPhone(String phone) {
        return (phone == null || phone.isEmpty() || !Pattern.matches("^1[3-9]\\d{9}$",phone))
                ? "***********" : phone.substring(0,3)+"******"+phone.substring(9,11);
    }
}
