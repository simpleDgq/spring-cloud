package com.dgq.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order") // 自动刷新nacos配置，能读到最新的值，不需要@RefreshScope
@Data
public class OrderProperties {
    String timeout;
    String autoConfirm;
    String dbUrl;
}
