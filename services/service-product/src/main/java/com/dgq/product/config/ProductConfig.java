package com.dgq.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductConfig {
    /**
     * RestTemplate是spring提供的线程安全的http客户端，用于发送http请求
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
