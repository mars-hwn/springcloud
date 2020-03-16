package com.alonginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Author:hwn
 * Date:2020-02-27 10:44
 * Description:<描述>
 */
@SpringBootApplication(scanBasePackages = "com.alonginfo")
@EnableFeignClients(basePackages = "com.alonginfo")
@EnableEurekaClient
public class PsmpClientApp {

    public static void main(String[] args) {
        SpringApplication.run(PsmpClientApp.class,args);
        System.out.println("====== psmp client 启动成功======");
    }
}
