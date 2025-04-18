package com.dgq.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

// value是第三方微服务名字，可以随便写
// url是调用的地址
@FeignClient(value = "weather-client", url = "https://ali-weather.showapi.com")
public interface WeatherFeignClient {

    /**
     * RequestParam注解将传入的areaCode绑定到请求参数里面
     * RequestHeader注解将传入的appCode绑定到请求头里面
     *
     * 相当于是个相反的方向，传入的参数，被传递到调用的api里面
     */
    @GetMapping("/weatherhistory")
    String getWeather(@RequestParam("areCode") String areaCode,
                      @RequestParam("area") String area,
                      @RequestParam("month") String month,
                      @RequestParam("startDate") String startDate,
                      @RequestParam("endDate") String endDate,
                      @RequestHeader("Authorization") String appCode);
}
