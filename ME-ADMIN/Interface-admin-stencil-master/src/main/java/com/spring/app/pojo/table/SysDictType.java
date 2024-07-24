package com.spring.app.pojo.table;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName: SysDictType
 * @Date: 2023/11/5 23:16
 * @Description:
 **/

@Data
@Table("sys_dict_type")
public class SysDictType {
    private Long id;

    @NotBlank(message = "字典名称不能为空！")
    private String dict_name;

    @NotBlank(message = "字典类型不能为空！")
    @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典标识必须以字母开头 | 且只能为(小写字母，数字，下滑线)")
    private String dict_type;

    private String status;

    private String create_time;

    private String remark;

}
