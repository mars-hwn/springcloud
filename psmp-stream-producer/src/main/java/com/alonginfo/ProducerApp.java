package com.alonginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author:hwn
 * Date:2020-03-11 11:33
 * Description:<描述>
 */
@SpringBootApplication(scanBasePackages = "com.alonginfo")
public class ProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class,args);
        System.out.println("====== psmp stream producer 启动成功======");
    }
}
