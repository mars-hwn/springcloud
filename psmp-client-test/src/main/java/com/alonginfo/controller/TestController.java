package com.alonginfo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:hwn
 * Date:2020-03-03 14:52
 * Description:<描述>
 */
@RestController
public class TestController {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "ok";
    }

}
