package com.atguigu.springcloud.serivce;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentService {
    @GetMapping(value = "payment/get/{id}")
    CommonResult<Payment> getById(@PathVariable("id") Integer id);

    @GetMapping(value = "payment/paymentFeignTimeOut")
    String paymentTimeOut();
}
