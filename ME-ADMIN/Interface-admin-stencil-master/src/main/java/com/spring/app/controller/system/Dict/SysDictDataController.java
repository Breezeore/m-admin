package com.spring.app.controller.system.Dict;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.spring.app.response.RespData;
import com.spring.app.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation. RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysDictionaryController
 * @Date: 2023/11/5 23:12
 * @Description: 字典数据控制器
 **/
@RestController
@RequestMapping("/sys/dictData")
public class SysDictDataController {
    @Autowired
    private SysDictionaryService sysDictionaryService;

    @GetMapping("/getDictData/{type}")
    @SaCheckLogin
    public RespData getDictData(@PathVariable String type) {
        return sysDictionaryService.getDictData(type);
    }
}
