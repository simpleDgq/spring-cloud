package com.dgq.order.service;


import com.dgq.order.Order;

public interface OrderService {

    Order createOrder(Long productId, Long userId);
}
