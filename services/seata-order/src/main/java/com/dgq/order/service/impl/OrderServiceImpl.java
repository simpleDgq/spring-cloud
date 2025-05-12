package com.dgq.order.service.impl;

import com.dgq.order.bean.OrderTbl;
import com.dgq.order.feign.AccountFeignClient;
import com.dgq.order.mapper.OrderTblMapper;
import com.dgq.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderTblMapper orderTblMapper;

    @Autowired
    AccountFeignClient accountFeignClient;

    @Transactional // 测试本地事务，本地事务发生异常，会回滚
    @Override
    public OrderTbl create(String userId, String commodityCode, int orderCount) {
        //1、计算订单价格
        int orderMoney = calculate(commodityCode, orderCount);

        //2、扣减账户余额
        accountFeignClient.debit(userId, orderMoney); // 远程调用
        //3、保存订单
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setUserId(userId);
        orderTbl.setCommodityCode(commodityCode);
        orderTbl.setCount(orderCount);
        orderTbl.setMoney(orderMoney);

        //3、保存订单
        orderTblMapper.insert(orderTbl);

        /**
         * 发生异常，只有当前create方法对应的数据库连接，也就是order数据库里面的数据会回滚
         * accout和storage对应的数据库修改，是不会回滚的
         * 出现了分布式服务，不能同时进行回滚的问题
         */
        int i = 10/0;

        return orderTbl;
    }

    // 计算价格
    private int calculate(String commodityCode, int orderCount) {
        return 9*orderCount;
    }
}
