package com.spring.app.mapper.system;

import com.mybatisflex.core.BaseMapper;
import com.spring.app.pojo.table.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: AdminMapper
 * @Date: 2023/10/29 1:09
 * @Description:
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
