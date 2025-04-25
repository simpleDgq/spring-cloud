package com.dgq.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dgq.order.Order;
import com.dgq.order.properties.OrderProperties;
import com.dgq.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope // 激活配置自动刷新功能
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 获取Nacos中的配置
//    @Value("${order.timeout}")
//    String orderTimeout;
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/create")
    public Order createOrder(@RequestParam Long productId, @RequestParam Long userId) {

        System.out.println("hello");
        Order order = orderService.createOrder(productId, userId);
        return order;
    }

    /**
     * 创建订单-秒杀
     */
    @GetMapping("/seckill")
    // 自定义流控埋点
    // 注意自定义的埋点资源名，不能和sentinel里面默认会扫描的web接口资源名一样。也就是@GetMapping("/seckill")里面的/seckill
    // 否则会重复统计，造成bug
    @SentinelResource(value = "kill-order", fallback =  "killOrderFallback")
    public Order createKillOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }

    /**
     * fallback和blockHandler区别：
     *  fallback指定的兜底回调，异常使用用Throwable，能处理所有的异常，比如业务执行期间抛出的异常
     * blockHandler，它只能处理BlockedException
     *
     */
    public Order killOrderFallback(Long userId, Long productId, Throwable e) {
        log.info("fallback.....................");
        Order order = new Order() {{
            setId(-1L);
            setAddress("商品已下架");
        }};

        return order;
    }


    @GetMapping("getConfig")
    public String getConfig() {
//        return "orderTimeout: " + orderTimeout + ": orderAutoConfirm: " + orderAutoConfirm;
        return "orderTimeout: " + orderProperties.getTimeout() + ": orderAutoConfirm: " + orderProperties.getAutoConfirm()
                + ": orderDbUrl: " + orderProperties.getDbUrl();
    }

    /**
     * 写数据
     */
    @GetMapping("/writedb")
    public String writeData() {
        return "write data success";
    }
    /**
     * 读数据
     */
    @GetMapping("/readdb")
    public String readData() {
        log.info("read success......");
        return "read data success";
    }
}
