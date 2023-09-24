package org.example.ylb;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 邓和颖
 */ // 启用 dubbo 服务
@EnableDubbo
@EnableSwagger2
@EnableSwaggerBootstrapUI
@SpringBootApplication
public class MicrWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrWebApplication.class, args);
	}

}
