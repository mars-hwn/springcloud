package com.alonginfo.psmpportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(scanBasePackages = "com.alonginfo")
@EnableEurekaClient
public class PsmpPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsmpPortalApplication.class, args);
		System.out.println("====== psmp portal 启动成功======");
	}

}

