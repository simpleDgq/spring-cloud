package com.dgq.account.service.impl;

import com.dgq.account.mapper.AccountTblMapper;
import com.dgq.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountTblMapper accountTblMapper;

    @Override
    @Transactional // 测试本地事务，本地事务发生异常，会回滚
    public void debit(String userId, int money) {
        // 扣减账户余额
        accountTblMapper.debit(userId,money);
    }
}
