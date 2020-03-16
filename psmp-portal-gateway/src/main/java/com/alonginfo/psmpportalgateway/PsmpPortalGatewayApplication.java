package com.alonginfo.psmpportalgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.alonginfo")
@EnableDiscoveryClient
public class PsmpPortalGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsmpPortalGatewayApplication.class, args);
		System.out.println("====== psmp portal gateway 启动成功======");
	}

}

