package com.spring.app.service;

import com.spring.app.pojo.table.SysDictType;
import com.spring.app.response.RespData;
import com.spring.app.response.other.Pagination;

/**
 * @ClassName: SysDictionaryService
 * @Date: 2023/11/5 23:37
 * @Description:
 **/
public interface SysDictionaryService {
    RespData getDictList(Pagination pagination);

    RespData getDictData(String type);

    RespData addDict(SysDictType sysDictType);

    RespData upDict(SysDictType sysDictType);

    RespData delDict(String type);
}
