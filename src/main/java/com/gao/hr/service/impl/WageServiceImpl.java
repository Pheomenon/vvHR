package com.gao.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.MyException;
import com.gao.hr.entity.Wage;
import com.gao.hr.mapper.WageMapper;
import com.gao.hr.service.WageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author gao
 * @since 2020-04-14
 */
@Service
public class WageServiceImpl extends ServiceImpl<WageMapper, Wage> implements WageService {

    @Override
    public void checkRecordExist(Wage wage) {
        QueryWrapper<Wage> wrapper = new QueryWrapper<>();
        wrapper.eq("department",wage.getDepartment());
        wrapper.eq("name",wage.getName());
        wrapper.eq("month",wage.getMonth());
        if(this.baseMapper.selectCount(wrapper) != 0){
            throw new MyException(20001,"添加失败！"+"该员工在"+wage.getMonth()+"月已有记录，请查正后重新添加！");
        }
    }
}
