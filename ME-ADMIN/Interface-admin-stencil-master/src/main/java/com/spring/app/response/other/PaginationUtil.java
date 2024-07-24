package com.spring.app.response.other;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableDef;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PaginationByMapper
 * @Date: 2023/11/13 9:29
 * @Description:
 **/
public class PaginationUtil{

    public static QueryWrapper getMapper(TableDef tableName, Pagination pagination) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        queryWrapper.from(tableName);


        if (pagination.getQuery() == null) {
            return queryWrapper;
        }

        // 遍历查询query所携带的所有数据，如果有key有数据就查询，如果没有就不查询,不要再去写烦人的if else了
        pagination.getQuery().forEach((key, value) -> {
            if (value != null) {
                queryWrapper.where(key + " LIKE ?", "%" + value + "%");
            }
        });


        return queryWrapper;
    }

    //将一个object对象转换为Pagination对象
    public static Pagination getPagination(Map<String, Object> data) {
        Pagination pagination = new Pagination();

        // 提取data中的page和limit，使用默认值
        int page = Integer.parseInt(data.getOrDefault("page", "1").toString());
        int limit = Integer.parseInt(data.getOrDefault("limit", "10").toString());

        // 设置page和limit
        pagination.setPage(page);
        pagination.setLimit(limit);

        // 将剩余所有的数据都放入query中
        Map<String, Object> query = new HashMap<>(data);
        query.remove("page");
        query.remove("limit");

        pagination.setQuery(query);
        return pagination;
    }
}
