package com.gao.hr.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author:Gao
 * @Date:2020-04-21 22:10
 */
@Data
public class EmployeeInfo {
    @ExcelProperty("所属部门")
    private String departmentName;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("电话")
    private String tel;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("身份证号")
    private String idCard;

    @ExcelProperty("籍贯")
    private String hometown;

    @ExcelProperty("出生日期")
    private String born;

    @ExcelProperty("毕业院校")
    private String graduatedSchool;

    @ExcelProperty("学历")
    private String culture;

    @ExcelProperty("政治面貌")
    private String politicalStatus;

    @ExcelProperty("婚姻状况")
    private String marriage;

    @ExcelProperty("职级")
    private String level;

    @ExcelProperty("职位")
    private String position;
}
