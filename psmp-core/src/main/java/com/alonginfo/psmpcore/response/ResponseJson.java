package com.alonginfo.psmpcore.response;


import com.google.gson.Gson;

/**
 * 响应返回json对象
 *
 * @author rayjp
 * @create 2018-05-30
 **/
public class ResponseJson {

    private Integer code;
    private String msg;
    private Object data;

    public ResponseJson() {
    }

    public ResponseJson(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseJson(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResponseCode(ResponseCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    public static ResponseJson success() {
        return success(null);
    }

    public static ResponseJson success(Object data){
        ResponseJson result = new ResponseJson();
        result.setResponseCode(ResponseCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ResponseJson failure(ResponseCode responseCode) {
        return failure(responseCode,null);
    }

    public static ResponseJson failure(ResponseCode responseCode, Object data) {
        ResponseJson result = new ResponseJson();
        result.setResponseCode(responseCode);
        result.setData(data);
        return result;
    }

    public String toJsonString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
