package com.alonginfo.psmpcore.exception;

import com.alonginfo.psmpcore.response.ResponseCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rayjp
 * @create 2018-06-15
 **/

@RestController
public class MyErrorController implements ErrorController {

    private static final String PATH = "/error";
    @RequestMapping(PATH)
    public void errorHandler(){
        throw new AlongException(ResponseCode.SYSTEM_INNER_ERROR);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
