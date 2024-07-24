package com.spring.app.pojo.table;

import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @ClassName: SysDictType
 * @Date: 2023/11/5 23:16
 * @Description:
 **/

@Data
@Table("sys_dict_data")
public class SysDictData {
    private Long Integer;

    private String dict_sort;

    private String dict_label;

    private String dict_value;

    private String dict_type;

    private String status;

    private String create_time;

    private String info;

    private String remark;


}
