package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping(value = "add")
    public CommonResult save(Payment payment){
        Integer result = paymentService.add(payment);
        return new CommonResult(200,"成功,serverPort"+serverPort);
    }
    @GetMapping(value = "get/{id}")
    public CommonResult getById(@PathVariable("id") Integer id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("数据:"+payment);
        return new CommonResult(200,"成功,serverPort"+serverPort,payment);
    }
}


