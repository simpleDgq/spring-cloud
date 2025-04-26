package com.dgq.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient // 开启服务注册，应用启动的时候，注册到nacos中
@SpringBootApplication
public class GatewayMainApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApplicaiton.class);
    }
}