package com.dgq.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component // 放入容器中，会自动应用这个bean进行feign请求拦截
@Slf4j
public class XTokenRequestInterceptor implements RequestInterceptor {
    /**
     * 请求拦截器
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("feignClient Request X-Token Interceptor ");
        requestTemplate.header("X-Token", UUID.randomUUID().toString());
    }
}
