package com.spring.app.config.saToken;

import cn.dev33.satoken.stp.StpInterface;
import com.mybatisflex.core.query.QueryWrapper;
import com.spring.app.mapper.system.UserMapper;
import com.spring.app.pojo.table.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.spring.app.pojo.table.table.UserTableDef.USER;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: StpInterfaceImpl
 * @Date: 2023/11/7 11:15
 * @Description: 权限配置
 **/
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserMapper userMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();

        QueryWrapper queryWrapper = new QueryWrapper()
                .select(USER.ALL_COLUMNS)
                .from(USER)
                .where(USER.ID.eq(loginId.toString()));

        User user = userMapper.selectOneByQuery(queryWrapper);

        if(user == null || user.getRole() == null){
            return list;
        }

        if(user.getRole().equals("super-admin")){
            list.add("*");
            return list;
        }

        //不是超级管理...

        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {

        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }

}
