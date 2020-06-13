package com.gao.hr.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author:Gao
 * @Date:2020-06-10 21:49
 */
@Data
public class CjVo {

    private String name;

    private String idCard;

    private String departmentName;

    private String title;

    private Integer type;

    private String content;

    private String money;

    private Date time;
}
