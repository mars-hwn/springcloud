package com.alonginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author:hwn
 * Date:2020-03-11 11:30
 * Description:<描述>
 */
@SpringBootApplication(scanBasePackages = "com.alonginfo")
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class,args);
        System.out.println("====== psmp stream consumer 启动成功======");
    }
}
