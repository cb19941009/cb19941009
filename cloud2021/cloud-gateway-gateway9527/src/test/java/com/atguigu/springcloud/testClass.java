package com.atguigu.springcloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testClass {
    @Test
    public void dd(){
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println(dateTime);
    }
}
