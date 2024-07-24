package com.spring.app.response;

import org.springframework.stereotype.Component;

/**
 * @ClassName RespData
 * @Date 2023/6/3 9:21
 * @Author BREEZE
 **/
@Component
public class RespData {
    private Object data;
    private Integer code;
    private Object message;

    public RespData() {
    }

    @Override
    public String toString() {
        return "RespData{" +
                "data=" + data +
                ", code=" + code +
                ", message=" + message +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public RespData(Object data, Integer code, Object message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
}
