package com.dgq.storage.service.impl;

import com.dgq.storage.mapper.StorageTblMapper;
import com.dgq.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageTblMapper storageTblMapper;



    @Transactional // 测试本地事务，本地事务发生异常，会回滚
    @Override
    public void deduct(String commodityCode, int count) {
        storageTblMapper.deduct(commodityCode, count);
        if (count == 5) {
            throw new RuntimeException("库存不足"); // 本地事务发生，异常数据会回滚
        }
    }
}
