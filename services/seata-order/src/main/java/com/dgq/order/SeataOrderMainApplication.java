package com.dgq.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.dgq.order.feign")
@MapperScan("com.dgq.order.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class SeataOrderMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApplication.class, args);
    }


}
