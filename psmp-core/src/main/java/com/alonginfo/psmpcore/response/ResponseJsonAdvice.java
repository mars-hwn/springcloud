package com.alonginfo.psmpcore.response;

import com.alonginfo.psmpcore.exception.AlongException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

/**
 * 统一返回json对象处理
 *
 * @author rayjp
 * @create 2018-05-30
 **/
@RestControllerAdvice
public class ResponseJsonAdvice implements ResponseBodyAdvice {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof ResponseJson) {
            return o;
        }else if(o instanceof Resource){
            return o;
        }else if(o instanceof String){
            try {
                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                return mapper.writeValueAsString(ResponseJson.success(o));
            } catch (JsonProcessingException e) {
                throw new AlongException("json 序列号错误");
            }
        }
        return ResponseJson.success(o == null ? new HashMap<>() : o);
    }
}
