package com.dgq.order.config;

import feign.Logger;
import feign.Retryer;
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

    /**
     * 开启feign日志
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 开启feign重试逻辑
     * @return
     */
    @Bean
    Retryer retryer() {
        return new Retryer.Default(); // 使用feign默认的重试逻辑，Default也有参数可以自己设置
    }
}
