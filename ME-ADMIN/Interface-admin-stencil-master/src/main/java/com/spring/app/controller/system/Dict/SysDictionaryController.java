package com.spring.app.controller.system.Dict;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.spring.app.aop.NoSubmit.Idempotent;
import com.spring.app.pojo.table.SysDictType;
import com.spring.app.response.RespData;
import com.spring.app.response.other.PaginationUtil;
import com.spring.app.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SysDictionaryController
 * @Date: 2023/11/5 23:12
 * @Description: 字典控制器
 **/
@RestController
@RequestMapping("/sys/dictionary")
public class SysDictionaryController {
    @Autowired
    private SysDictionaryService sysDictionaryService;


    @GetMapping("/getDictList")
    @SaCheckLogin
    @SaCheckPermission(value = {"*"}, mode = SaMode.OR)
    public RespData getDictList(@RequestParam Map<String,Object> data) {
        return sysDictionaryService.getDictList(PaginationUtil.getPagination(data));
    }

    @PostMapping("/addDict")
    @SaCheckLogin
    @SaCheckPermission(value = {"*"}, mode = SaMode.OR)
    @Idempotent(isIdempotent = true,expireTime = 3,timeUnit = TimeUnit.SECONDS,info = "您操作的太快了 | 请勿重复提交!",delKey = false)
    public RespData addDict(@RequestBody @Validated SysDictType sysDictType) {
        return sysDictionaryService.addDict(sysDictType);
    }


    @PutMapping("/upDict")
    @SaCheckLogin
    @SaCheckPermission(value = {"*"}, mode = SaMode.OR)
    @Idempotent(isIdempotent = true,expireTime = 3,timeUnit = TimeUnit.SECONDS,info = "您操作的太快了 | 请勿重复提交!",delKey = false)
    public RespData upDict(@RequestBody @Validated SysDictType sysDictType) {
        return sysDictionaryService.upDict(sysDictType);
    }

    @DeleteMapping("/delDict/{type}")
    @SaCheckLogin
    @SaCheckPermission(value = {"*"}, mode = SaMode.OR)
    public RespData delDict(@PathVariable String type) {
        return sysDictionaryService.delDict(type);
    }


}
