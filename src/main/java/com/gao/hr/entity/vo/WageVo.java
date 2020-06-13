package com.gao.hr.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:Gao
 * @Date:2020-06-10 17:48
 */
@Data
public class WageVo {
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

    private Integer wageId;

    private Integer level;
}
