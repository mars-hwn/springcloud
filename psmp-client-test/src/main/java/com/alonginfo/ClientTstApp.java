package com.alonginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author:hwn
 * Date:2020-03-03 14:51
 * Description:<描述>
 */
@SpringBootApplication(scanBasePackages = "com.alonginfo")
public class ClientTstApp {

    public static void main(String[] args) {

        SpringApplication.run(ClientTstApp.class,args);
    }
}
