package com.rainy.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rainy.spider.mapper")
public class WeiboVisualizationSpiderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeiboVisualizationSpiderApplication.class, args);
    }

}
