package com.gao.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.MyException;
import com.gao.hr.entity.Department;
import com.gao.hr.entity.Department;
import com.gao.hr.mapper.DepartmentMapper;
import com.gao.hr.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author gao
 * @since 2020-04-14
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public void checkExist(Department department) {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("name",department.getName());
        if(this.baseMapper.selectCount(wrapper) != 0){
            throw new MyException(20001,"添加失败!"+"该部门已存在，请勿重复添加！");
        }
    }
}
