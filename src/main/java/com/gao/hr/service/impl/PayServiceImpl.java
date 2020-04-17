package com.gao.hr.service.impl;

import com.gao.hr.entity.Wage;
import com.gao.hr.mapper.PayMapper;
import com.gao.hr.service.PayService;
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
public class PayServiceImpl extends ServiceImpl<PayMapper, Wage> implements PayService {

}
