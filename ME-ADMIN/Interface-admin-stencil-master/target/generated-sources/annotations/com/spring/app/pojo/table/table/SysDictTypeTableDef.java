package com.spring.app.pojo.table.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class SysDictTypeTableDef extends TableDef {

    /**
     * @ClassName: SysDictType
 @Date: 2023/11/5 23:16
 @Description:
     */
    public static final SysDictTypeTableDef SYS_DICT_TYPE = new SysDictTypeTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    public final QueryColumn STATUS = new QueryColumn(this, "status");

    public final QueryColumn DICT_NAME = new QueryColumn(this, "dict_name");

    public final QueryColumn DICT_TYPE = new QueryColumn(this, "dict_type");

    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, REMARK, STATUS, DICT_NAME, DICT_TYPE, CREATE_TIME};

    public SysDictTypeTableDef() {
        super("", "sys_dict_type");
    }

}
