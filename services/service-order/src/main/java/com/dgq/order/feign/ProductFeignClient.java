package com.dgq.order.feign;

import com.dgq.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product")
public interface ProductFeignClient {
    /**
     * 在Openfegin是发送get请求
     */
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
