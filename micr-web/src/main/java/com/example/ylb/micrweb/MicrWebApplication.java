package com.example.ylb.micrweb;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 启用 dubbo 服务
@EnableDubbo
@SpringBootApplication
public class MicrWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrWebApplication.class, args);
	}

}
