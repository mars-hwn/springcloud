package com.alonginfo.psmpportalwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.alonginfo")
@EnableEurekaClient
public class PsmpPortalWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsmpPortalWebsiteApplication.class, args);
        System.out.println("====== psmp portal website 启动成功======");
    }

}

