package org.example.ylb.front.service;

/**
 * @author 邓和颖
 * @since 2023/10/5 10:36
 */
public interface SmsService {

    // 短信服务
    boolean sendSms(String phone);

    // 比对验证码
    boolean checkSmsCode(String phone,String code);
}
