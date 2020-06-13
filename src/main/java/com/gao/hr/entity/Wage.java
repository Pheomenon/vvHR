package com.gao.hr.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
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

    private String departmentName;

    private String name;

    private String date;

    private BigDecimal basicWage;

    private BigDecimal overtime;

    private BigDecimal payCheck;

    private BigDecimal fine;

    private BigDecimal bonus;

    private String idCard;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

}
