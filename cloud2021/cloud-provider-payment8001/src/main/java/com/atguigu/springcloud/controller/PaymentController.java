package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;

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
    @GetMapping(value = "/discovery")
    public Object discovery(){
        List<String> service = discoveryClient.getServices();
        service.forEach(System.out::println);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info(instance.getServiceId()+instance.getHost()+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/paymentFeignTimeOut")
    public String paymentFeignTimeOut(){
        try {
            System.out.println("aaaaaaaaaaa");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }
}


