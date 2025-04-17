package com.dgq.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {
    /**
     * RestTemplate是spring提供的线程安全的http客户端，用于发送http请求
     */
    @LoadBalanced // 使RestTemplate自动支持负载均衡，不需要使用loadBalancerClient实现了
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
