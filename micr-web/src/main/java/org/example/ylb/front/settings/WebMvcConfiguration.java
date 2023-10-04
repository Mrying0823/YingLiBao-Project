package org.example.ylb.front.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 邓和颖
 * @since 2023/9/30 9:55
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /***
     * // 拦截所有的请求
     * addMapping("/**")
     *
     * // 可跨域的域名，可以为 *
     * .allowedOriginPatterns("*")
     *
     *  // 允许跨域的方法，可以单独配置
     * allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
     *
     * //该字段可选。它的值是一个布尔值，表示是否允许浏览器发送Cookie. true:支持发送cookie
     * allowCredentials(true)
     *
     * //该字段可选，用来指定本次预检请求的有效期，单位为秒。在有效期间，不用发出另一条预检请求
     * maxAge(3600)
     *
     * // 允许跨域的请求头，可以单独配置
     * allowedHeaders("*");
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("===========addCorsMappings===========");
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
