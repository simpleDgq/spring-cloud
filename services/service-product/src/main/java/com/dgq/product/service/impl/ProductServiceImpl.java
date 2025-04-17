package com.dgq.product.service.impl;
import java.math.BigDecimal;

import com.dgq.product.Product;
import com.dgq.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("iphone " + productId);
        product.setNum(12);
        return product;
    }
}
