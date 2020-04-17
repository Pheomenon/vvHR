package com.gao.hr.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gao
 * @since 2020-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="奖惩对象", description="")
public class Cj implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private Integer type;

    private String content;

    private String money;

    @TableField(fill = FieldFill.INSERT)
    private Date time;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;
}
