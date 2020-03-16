package com.alonginfo.psmpportal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:hwn
 * Date:2020-02-27 10:22
 * Description:<描述>
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public String test(){
        return "111";
    }
}
