package com.gao.hr.service.impl;

import com.gao.hr.entity.Employee;
import com.gao.hr.mapper.EmployeeMapper;
import com.gao.hr.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gao
 * @since 2020-04-14
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
