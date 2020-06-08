package com.gao.hr.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gao
 * @since 2020-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
//生成setter方法返回this，代替了默认的返回void。
@Accessors(chain = true)
@ApiModel(value="部门", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Date createTime;

    private String bz;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;
}