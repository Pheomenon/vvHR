package com.gao.hr.service;

import com.gao.hr.entity.Wage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author gao
 * @since 2020-04-14
 */
public interface WageService extends IService<Wage> {

    void checkRecordExist(Wage wage);
}
