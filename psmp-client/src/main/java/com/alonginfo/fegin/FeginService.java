package com.alonginfo.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:hwn
 * Date:2020-03-05 13:37
 * Description:<描述>
 */
@FeignClient(name = "psmp-client-test")
public interface FeginService {

    @RequestMapping(value = "/portal/hello")
    public String hello();
}
