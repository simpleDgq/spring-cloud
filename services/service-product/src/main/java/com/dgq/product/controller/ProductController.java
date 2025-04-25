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
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId, HttpServletRequest request) {

        System.out.println(request.getHeader("X-token"));

        Product product = productService.getProductById(productId);
        System.out.println("product");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return product;
    }
}
