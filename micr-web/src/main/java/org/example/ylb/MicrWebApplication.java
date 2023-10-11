package org.example.ylb;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.example.ylb.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 邓和颖
 */ // 启用 dubbo 服务
@EnableDubbo
@EnableSwagger2
@EnableSwaggerBootstrapUI
@SpringBootApplication
public class MicrWebApplication {

	@Value("${jwt.data.secret}")
	private String secret;

	@Value("${jwt.data.expiration}")
	private long expiration;

	// 创建 JwtUtils 对象
	@Bean
	public JwtUtils jwtUtils() {
		return new JwtUtils(secret,expiration);
	}

	public static void main(String[] args) {
		SpringApplication.run(MicrWebApplication.class, args);
	}

}
