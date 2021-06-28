package com.atguigu.springcloud.lb;

import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger =new AtomicInteger(0);

    public final  int getAndIncrement(){
        System.out.println(atomicInteger);
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current>=Integer.MAX_VALUE?0:current+1;
        }while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("next:"+next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> instances) {
        Integer index =getAndIncrement()%instances.size();
        return instances.get(index);
    }
}
