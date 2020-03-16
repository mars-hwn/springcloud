package com.alonginfo.psmpeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PsmpEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsmpEurekaServerApplication.class, args);
	}

}

