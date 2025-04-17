package com.dgq.order.controller;

import com.dgq.order.Order;
import com.dgq.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public Order createOrder(@RequestParam Long productId, @RequestParam Long userId) {

        System.out.println("hello");
        Order order = orderService.createOrder(productId, userId);
        return order;
    }
}
