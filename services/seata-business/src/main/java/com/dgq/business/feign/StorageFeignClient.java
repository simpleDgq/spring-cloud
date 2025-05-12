package com.dgq.business.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage") // value指定要调用的服务名
public interface StorageFeignClient {

    /**
     * 扣减库存
     * @param commodityCode
     * @param count
     * @return
     */
    @GetMapping("/deduct")
    String deduct(@RequestParam("commodityCode") String commodityCode,
                         @RequestParam("count") Integer count);
}
