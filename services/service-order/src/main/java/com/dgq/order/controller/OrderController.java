package com.dgq.order.controller;

import com.dgq.order.Order;
import com.dgq.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // 激活配置自动刷新功能
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 获取Nacos中的配置
    @Value("${order.timeout}")
    String orderTimeout;
    @Value("${order.auto-confirm}")
    String orderAutoConfirm;

    @GetMapping("/create")
    public Order createOrder(@RequestParam Long productId, @RequestParam Long userId) {

        System.out.println("hello");
        Order order = orderService.createOrder(productId, userId);
        return order;
    }


    @GetMapping("getConfig")
    public String getConfig() {
        return "orderTimeout: " + orderTimeout + ": orderAutoConfirm: " + orderAutoConfirm;
    }
}
