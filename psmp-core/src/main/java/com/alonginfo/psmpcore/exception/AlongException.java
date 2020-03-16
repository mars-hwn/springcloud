package com.alonginfo.psmpcore.exception;


import com.alonginfo.psmpcore.response.ResponseCode;

public class AlongException extends RuntimeException{

    private Integer code;
    private String message;

    public AlongException() {
    }

    public AlongException(String msg) {
        super(msg);
    }

    public AlongException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public AlongException(ResponseCode responseCode) {
        this.code = responseCode.code();
        this.message = responseCode.message();
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
