package com.gao.hr.service.impl;

import com.alibaba.excel.EasyExcel;
import com.gao.hr.entity.Employee;
import com.gao.hr.entity.excel.EmployeeInfo;
import com.gao.hr.listener.EmployeeListener;
import com.gao.hr.mapper.EmployeeMapper;
import com.gao.hr.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
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
}
