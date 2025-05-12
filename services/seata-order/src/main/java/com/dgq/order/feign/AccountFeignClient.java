package com.dgq.order.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-account") // value指定要调用的微服务
public interface AccountFeignClient {

    /**
     * 扣减账户余额
     * @return
     */
    @GetMapping("/debit")
    String debit(@RequestParam("userId") String userId,
                        @RequestParam("money") int money);
}
