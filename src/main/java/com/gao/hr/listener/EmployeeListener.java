package com.gao.hr.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.gao.hr.entity.Employee;
import com.gao.hr.entity.excel.EmployeeInfo;
import com.gao.hr.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @Author:Gao
 * @Date:2020-04-21 22:18
 */
@Slf4j
public class EmployeeListener extends AnalysisEventListener<EmployeeInfo> {

    public EmployeeService employeeService;

    public EmployeeListener(){}

    public EmployeeListener(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Override
    public void invoke(EmployeeInfo employeeInfo, AnalysisContext context) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeInfo,employee);
        log.info(employeeInfo.toString());
        if(employeeInfo.getSex() != null){
            if(employeeInfo.getSex().equals("女")){
                employee.setSex(0);
            }else {
                employee.setSex(1);
            }
        }
        if(employeeInfo.getLevel() != null){
            if(employeeInfo.getLevel().equals("一级")){
                employee.setLevel(1);
            }
            else if(employeeInfo.getLevel().equals("二级")){
                employee.setLevel(2);
            }
            else if(employeeInfo.getLevel().equals("三级")){
                employee.setLevel(3);
            }
            else if(employeeInfo.getLevel().equals("四级")){
                employee.setLevel(4);
            }
            else if(employeeInfo.getLevel().equals("五级")){
                employee.setLevel(5);
            }
            else{
                employee.setLevel(1);
            }
        }
        if(employeeInfo.getPoliticalStatus() != null){
            if(employeeInfo.getPoliticalStatus().equals("党员")){
                employee.setPoliticalStatus(0);
            }else {
                employee.setPoliticalStatus(1);
            }
        }
        if(employeeInfo.getMarriage() != null){
            if(employeeInfo.getMarriage().equals("已婚")){
                employee.setMarriage(0);
            }else {
                employee.setMarriage(1);
            }
        }

        employeeService.save(employee);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
