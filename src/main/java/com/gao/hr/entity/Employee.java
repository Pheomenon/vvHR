package com.gao.hr.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
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
@ApiModel(value="Employee", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private String idCard;

    private Date born;

    private String nation;

    private String marriage;

    private String politicalStatus;

    private String hometown;

    private String tel;

    private String address;

    private String graduatedSchool;

    private String speciality;

    private String culture;

    private Date startTime;

    private String typeWork;

    private Date createTime;

    private String bz;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

}