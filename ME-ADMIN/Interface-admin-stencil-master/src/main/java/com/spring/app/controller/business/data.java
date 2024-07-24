package com.spring.app.controller.business;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.spring.app.pojo.table.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: data
 * @Date: 2023/11/5 19:53
 * @Description:
 **/
@RestController
@RequestMapping("/data")
public class data {


    @PostMapping("/get")
    @SaCheckLogin// 查询登录状态
    @SaCheckPermission(value = {"user-add", "*"}, mode = SaMode.OR)
    public SaResult isLogin(@RequestBody User user) {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }


}
