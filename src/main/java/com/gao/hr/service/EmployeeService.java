package com.gao.hr.service;

import com.gao.hr.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gao
 * @since 2020-04-14
 */
public interface EmployeeService extends IService<Employee> {

    void importEmployeeInfo(MultipartFile file, EmployeeService employeeService);
}
