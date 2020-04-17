package com.gao.hr.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gao
 * @since 2020-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="工资", description="")
public class Wage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String department;

    private String name;

    private Integer month;

    private Integer basicWage;

    private Integer overtime;

    private Integer age;

    private Integer payCheck;

    private Integer payAbsent;

    private Integer paySafety;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

}
