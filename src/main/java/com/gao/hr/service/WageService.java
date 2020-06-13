package com.gao.hr.service;

import com.gao.hr.entity.Cj;
import com.gao.hr.entity.Wage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.hr.entity.vo.CjVo;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author gao
 * @since 2020-04-14
 */
public interface WageService extends IService<Wage> {

    void checkRecordExist(Wage wage);

    boolean saveCj(Cj cj);

    void updateCj(Cj cj, String moneyBeforeModification);

    void updateCjInTypeChange(Cj cj, String money);

    void deleteCj(String type, String money, Cj cj);

//    void storeSelected(Wage wage);
//
//    List<Integer> takeSelected();
}
