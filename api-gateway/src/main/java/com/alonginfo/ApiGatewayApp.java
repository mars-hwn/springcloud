package com.alonginfo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author:hwn
 * Date:2020-02-27 15:24
 * Description:<描述>
 */
@SpringBootApplication(scanBasePackages = "com.alonginfo")
@EnableApolloConfig
public class ApiGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApp.class,args);
        System.out.println("====== api gateway 启动成功======");
    }
}
