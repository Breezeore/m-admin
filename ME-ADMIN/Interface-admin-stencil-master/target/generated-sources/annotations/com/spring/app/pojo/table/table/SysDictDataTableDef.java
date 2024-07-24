package com.spring.app.pojo.table.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class SysDictDataTableDef extends TableDef {

    /**
     * @ClassName: SysDictType
 @Date: 2023/11/5 23:16
 @Description:
     */
    public static final SysDictDataTableDef SYS_DICT_DATA = new SysDictDataTableDef();

    public final QueryColumn INFO = new QueryColumn(this, "info");

    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    public final QueryColumn STATUS = new QueryColumn(this, "status");

    public final QueryColumn INTEGER = new QueryColumn(this, "integer");

    public final QueryColumn DICT_SORT = new QueryColumn(this, "dict_sort");

    public final QueryColumn DICT_TYPE = new QueryColumn(this, "dict_type");

    public final QueryColumn DICT_LABEL = new QueryColumn(this, "dict_label");

    public final QueryColumn DICT_VALUE = new QueryColumn(this, "dict_value");

    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{INFO, REMARK, STATUS, INTEGER, DICT_SORT, DICT_TYPE, DICT_LABEL, DICT_VALUE, CREATE_TIME};

    public SysDictDataTableDef() {
        super("", "sys_dict_data");
    }

}
