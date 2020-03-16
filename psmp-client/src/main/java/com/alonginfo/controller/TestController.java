package com.alonginfo.controller;

import com.alonginfo.fegin.FeginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Author:hwn
 * Date:2020-02-27 10:47
 * Description:<描述>
 */
@RestController
public class TestController {

    @Resource
    private FeginService feginService;

    @RequestMapping("/show")
    public String test(){
        String result = "123";
        return "结果"+ result;
    }

    @RequestMapping("/show2")
    public String test1(){
        return feginService.hello();
    }

}
