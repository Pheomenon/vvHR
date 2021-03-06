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
@Accessors(chain = true)
@ApiModel(value="Employee", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer sex;

    private Integer age;

    private Integer level;

    private String position;

    private String idCard;

    private String born;

    private String nation;

    private Integer marriage;

    private Integer politicalStatus;

    private String hometown;

    private String tel;

    private String address;

    private String graduatedSchool;

    private String speciality;

    private String culture;

    private Date startTime;

    private String departmentName;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    private String bz;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

}
