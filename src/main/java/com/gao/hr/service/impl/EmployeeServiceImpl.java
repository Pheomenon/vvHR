package com.gao.hr.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.MyException;
import com.gao.hr.entity.Employee;
import com.gao.hr.entity.Wage;
import com.gao.hr.entity.excel.EmployeeInfo;
import com.gao.hr.listener.EmployeeListener;
import com.gao.hr.mapper.EmployeeMapper;
import com.gao.hr.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author gao
 * @since 2020-04-14
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public void importEmployeeInfo(MultipartFile file, EmployeeService employeeService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, EmployeeInfo.class, new EmployeeListener(employeeService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkExist(Employee employee) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id_card", employee.getIdCard());
        if (this.baseMapper.selectCount(wrapper) != 0) {
            Employee existedEmployee = baseMapper.selectOne(wrapper);
            throw new MyException(20001, "添加失败!" + "该员工与" + existedEmployee.getName() + "的身份证号相同" + ",请查正后重新添加！");
        }
    }

    @Override
    public boolean checkEmployeeInDepartment(Employee employee) {
        System.out.println(employee.toString());
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("department_name",employee.getDepartmentName());
        wrapper.eq("name",employee.getName());
        System.out.println(baseMapper.selectOne(wrapper));
        return this.baseMapper.selectCount(wrapper) != 0;
    }
}
