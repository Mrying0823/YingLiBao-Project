package org.example.ylb.front.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.example.ylb.common.constants.RedisKey;
import org.example.ylb.common.utils.HttpUtils;
import org.example.ylb.front.service.SmsService;
import org.example.ylb.front.settings.SmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 邓和颖
 * @since 2023/10/5 10:38
 */

// 短信服务不涉及分布式微服务架构，使用 Service 注解即可
@Service("smsService")
public class SmsServiceImpl implements SmsService {

    // 配置文件绑定类
    private SmsConfiguration smsConfiguration;

    // redis
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setSmsConfiguration(SmsConfiguration smsConfiguration) {
        this.smsConfiguration = smsConfiguration;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 注册发送短信验证码
    @Override
    public boolean sendSms(String phone) {

        // 发送短信失败
        boolean send = false;

        // 设置短信内容，6 位随机数
        String random = RandomStringUtils.randomNumeric(6);
        System.out.println("注册用验证码="+random);

        String host = smsConfiguration.getHost();
        String path = smsConfiguration.getPath();
        String method = smsConfiguration.getMethod();

        Map<String,String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();

        // url、AppCode，采用简单身份认证
        headers.put("Authorization", "APPCODE "+smsConfiguration.getAppCode());

        // 电话号码
        querys.put("mobile", phone);

        // 验证码
        querys.put("param", "**code**:"+random+",**minute**:5");

        // 签名
        querys.put("smsSignId", smsConfiguration.getSmsSignId());

        // 内容模板
        querys.put("templateId", smsConfiguration.getTemplateId());

        try {
            HttpResponse response = HttpUtils.doPost(host,path,method,headers,querys,bodys);

            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                String text = EntityUtils.toString(response.getEntity());

                // 获取 response 的 body（json），返回示例
                System.out.println(text);

                /*
                  {
                      "msg": "成功",
                      "smsid": "16565614329364584123421",  //批次号。可通过该ID查询发送状态或者回复短信。API接口可联系客服获取。
                      "code": "0",
                      "balance": "1234"  //账户剩余次数
                  }
                 */
                // 解析 json
                if(StringUtils.isNotBlank(text)) {

                    // fastjson
                    JSONObject jsonObject = JSONObject.parseObject(text);
                    if("0".equals(jsonObject.getString("code"))) {
                        // 第三方接口调用成功
                        send = true;

                        // 将短信验证码存入 redis
                        String key = RedisKey.KEY_SMS_CODE_REG+phone;
                        stringRedisTemplate.boundValueOps(key).set(random);

                        // 设置过期时间
                        stringRedisTemplate.expire(key,5, TimeUnit.MINUTES);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return send;
    }
}
