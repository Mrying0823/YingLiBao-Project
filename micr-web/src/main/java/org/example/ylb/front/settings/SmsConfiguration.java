package org.example.ylb.front.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 邓和颖
 * @since 2023/10/5 9:22
 */
@Component
@ConfigurationProperties(prefix = "api.sms")
public class SmsConfiguration {

    private String host;

    private String path;

    private String method;

    private String appkey;

    private String appSecret;

    private String appCode;

    private String smsSignId;

    private String templateId;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSmsSignId() {
        return smsSignId;
    }

    public void setSmsSignId(String smsSignId) {
        this.smsSignId = smsSignId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
