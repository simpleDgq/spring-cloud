package com.dgq.order.service.impl;

import com.dgq.order.Order;
import com.dgq.order.feign.ProductFeignClient;
import com.dgq.order.service.OrderService;
import com.dgq.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient ;

    @Autowired
    ProductFeignClient productFeignClient;

    @Override
    public Order createOrder(Long productId, Long userId) {

        // Product product = this.getProduct(productId);
        // loadBalancerClient实现负载均衡
//        Product product = this.getProductBalanced(productId);
        // 注解实现负载均衡
//        Product product = this.getProductBalancedAnnotation(productId);

        // 使用openfeign进行远程调用。openfeign自带负载均衡
        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("New York");
        order.setProducts(Arrays.asList(product));

        return order;
    }

    // 第一版：远程调用获取商品信息
    private Product getProduct(Long productId) {
        // 获取到商品服务所在的ip+端口
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;
        // 发送远程请求
        log.info("发送远程请求：{}", url);
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }

    // 第二版：远程调用获取商品信息 - loadBalancerClient实现负载均衡
    private Product getProductBalanced(Long productId) {
        // 获取到商品服务所在的ip+端口
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        // 发送远程请求
        log.info("发送远程请求：{}", url);
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }


    /**
     * 第三版：
     * 远程调用获取商品信息 - @LoadBalanced注解实现负载均衡
     * @LoadBalanced标记在RestTemplate上，restTemplate就自带负载均衡功能
     * 不需要使用loadBalancerClient实现
     */
    private Product getProductBalancedAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        // 发送远程请求
        log.info("发送远程请求：{}", url);
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }
}
