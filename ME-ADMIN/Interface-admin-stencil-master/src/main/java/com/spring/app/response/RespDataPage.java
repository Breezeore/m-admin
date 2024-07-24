package com.spring.app.response;

import org.springframework.stereotype.Component;

/**
 * @ClassName RespData
 * @Date 2023/6/3 9:21
 * @Author BREEZE
 **/
@Component
public class RespDataPage {
    private Object data;
    private Integer code;

    private Object count;
    private Object message;

    @Override
    public String toString() {
        return "RespDataPage{" +
                "data=" + data +
                ", code=" + code +
                ", count=" + count +
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

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        this.count = count;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public RespDataPage() {
    }

    public RespDataPage(Object data, Integer code, Object count, Object message) {
        this.data = data;
        this.code = code;
        this.count = count;
        this.message = message;
    }
}
