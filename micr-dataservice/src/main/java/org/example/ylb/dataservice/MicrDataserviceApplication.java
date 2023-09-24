package org.example.ylb.dataservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 邓和颖
 */ // 启动 dubbo 服务
@EnableDubbo
// Mapper 扫描包
@MapperScan(basePackages = "org.example.ylb.dataservice.mapper")
@SpringBootApplication
public class MicrDataserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrDataserviceApplication.class, args);
    }

}
