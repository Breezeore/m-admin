package com.spring.app.response.other;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName: Pagination
 * @Date: 2023/10/29 0:34
 * @Description:
 **/
@Data
public class Pagination {

    private Integer page;

    private Integer limit;

    //泛型的List，对象为任意类型
    private Map<String,Object>  query;


    //对page和limit进行判断，如果为空就赋值为1和10,page不能小于1，limit不能小于1，limit也不能大于50，如果大于50就赋值为50(这里主要是为了做限流，防止用户一次性查询过多数据，减少服务器压力)
    public void setPage(Integer page) {
        if (page == null || page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public void setLimit(Integer limit) {
        if (limit == null || limit < 1) {
            this.limit = 10;
        } else if (limit > 50) {
            this.limit = 50;
        } else {
            this.limit = limit;
        }
    }
}
