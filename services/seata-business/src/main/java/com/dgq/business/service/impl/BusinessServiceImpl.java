package com.dgq.business.service.impl;

import com.dgq.business.feign.OrderFeignClient;
import com.dgq.business.feign.StorageFeignClient;
import com.dgq.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    StorageFeignClient storageFeignClient;

    @Autowired
    OrderFeignClient orderFeignClient;


    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {
        // 1. 扣减库存
        storageFeignClient.deduct(commodityCode, orderCount);
        // 2. 创建订单
        orderFeignClient.create(userId, commodityCode, orderCount);

    }
}
