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
@TableName("tb_inviteJob")
@ApiModel(value="招聘", description="")
public class InviteJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private String born;

    private String job;

    private String specialty;

    private String experience;

    private String education;

    private String graduatedSchool;

    private String tel;

    private String address;

    private Date createTime;

    private String content;

    private Integer isHire;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;
}
