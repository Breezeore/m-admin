package com.spring.app.response;

/**
 * @ClassName Code
 * @Date 2023/6/3 9:23
 * @Author BREEZE
 **/
public class Code {
    //全局异常码
    public static final Integer SYSTEM_ERROR = 6666;
    public static final String  SYSTEM_ERROR_HINT = "服务不在线哦~";


    //token身份解析验证失败
    public static final Integer IDENTITY_ERROR = 606;
    public static final String IDENTITY_ERROR_HINT = "您的身份信息验证失败~";

    //角色权限不足
    public static final Integer ROLE_ERROR = 707;
    public static final String ROLE_ERROR_HINT = "当前系统角色权限不足哦~";

    //角色权限不足
    public static final Integer POWER_ERROR = 808;
    public static final String POWER_ERROR_HINT = "当前系统用户权限不足哦~";

    //全局成功失败匹配码
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String NULL = "null";
    //操作信息提示
    public static final String OPERATE_SUCCESS_HINT = "操作成功";
    public static final String OPERATE_ERROR_HINT = "操作失败";

    //常见业务状态码
    public static final Integer DATA_SUCCESS = 200;
    public static final Integer DATA_ERROR = 201;
    public static final String INSERT_SUCCESS_HINT = "添加成功";
    public static final String INSERT_ERROR_HINT = "添加失败";
    public static final String UPDATE_SUCCESS_HINT = "修改成功";
    public static final String UPDATE_ERROR_HINT = "修改失败";
    public static final String DELETE_SUCCESS_HINT = "删除成功";
    public static final String DELETE_ERROR_HINT = "删除失败";
    public static final String PAGE_SUCCESS_HINT = "查询成功";
    public static final String PAGE_ERROR_HINT = "查询失败";
    public static final String LOGIN_SUCCESS_HINT = "欢迎使用本系统";
    public static final String LOGIN_ERROR_HINT = "账号或密码不正确哦~";

}
