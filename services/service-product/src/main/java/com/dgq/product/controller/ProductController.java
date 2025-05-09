package com.dgq.product.controller;

import com.dgq.product.Product;
import com.dgq.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
// 通过gateway里面的filter重写路径了，不需要加全局路径
//@RequestMapping("/api/product") // 网关转发的时候，是直接按原始的uri转发，所以要加上全局的路径
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId, HttpServletRequest request) {

        System.out.println(request.getHeader("X-token"));

        Product product = productService.getProductById(productId);
        System.out.println("product");

//        int i = 10/0;

//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return product;
    }
}
