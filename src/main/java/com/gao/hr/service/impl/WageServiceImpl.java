package com.gao.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.MyException;
import com.gao.hr.entity.Cj;
import com.gao.hr.entity.Wage;
import com.gao.hr.entity.vo.CjVo;
import com.gao.hr.mapper.WageMapper;
import com.gao.hr.service.WageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author gao
 * @since 2020-04-14
 */
@Service
public class WageServiceImpl extends ServiceImpl<WageMapper, Wage> implements WageService {

//    static List<Integer> wageList = new LinkedList<>();

    @Override
    public void checkRecordExist(Wage wage) {
        QueryWrapper<Wage> wrapper = new QueryWrapper<>();
        wrapper.eq("department_name",wage.getDepartmentName());
        wrapper.eq("name",wage.getName());
        wrapper.eq("date",wage.getDate());
        if(this.baseMapper.selectCount(wrapper) != 0){
            throw new MyException(20001,"添加失败!"+"该员工在"+wage.getDate()+"已有记录，请查正后重新添加！");
        }
    }

    @Override
    public boolean saveCj(Cj cj) {
        String idCard = cj.getIdCard();
        String month = cj.getDate();
        QueryWrapper<Wage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_card",idCard);
        queryWrapper.eq("date",month);
//        type为1是奖励
        if(cj.getType().equals(1)){
            if(baseMapper.selectCount(queryWrapper) == 0){
                Wage wage = new Wage();
                BeanUtils.copyProperties(cj,wage);
                wage.setBonus(cj.getMoney());
                baseMapper.insert(wage);
            }else{
                Wage wage = new Wage();
                wage.setBonus(cj.getMoney());
                BeanUtils.copyProperties(cj,wage);
                QueryWrapper<Wage> wageWrapper = new QueryWrapper<>();
                wageWrapper.eq("id_card",cj.getIdCard());
                wageWrapper.eq("date",cj.getDate());
//                System.out.println(cj.getDate());
//                System.out.println(baseMapper.selectObjs(queryWrapper));
                BigDecimal bonusBeforeModify =  baseMapper.selectOne(queryWrapper).getBonus();
                BigDecimal bonusAfterModify = bonusBeforeModify.add(cj.getMoney());
                wage.setBonus(bonusAfterModify);
                baseMapper.update(wage,wageWrapper);
            }
        }
        if(cj.getType().equals(0)){
            if(baseMapper.selectCount(queryWrapper) == 0){
                Wage wage = new Wage();
                BeanUtils.copyProperties(cj,wage);
                wage.setFine(cj.getMoney());
                baseMapper.insert(wage);
            }else{
                Wage wage = new Wage();
                wage.setFine(cj.getMoney());
                BeanUtils.copyProperties(cj,wage);
                QueryWrapper<Wage> wageWrapper = new QueryWrapper<>();
                wageWrapper.eq("id_card",cj.getIdCard());
                wageWrapper.eq("date",cj.getDate());
                BigDecimal fineBeforeModify =  baseMapper.selectOne(queryWrapper).getFine();
                BigDecimal fineAfterModify = fineBeforeModify.add(cj.getMoney());
                wage.setFine(fineAfterModify);
                baseMapper.update(wage,wageWrapper);
            }
        }
        return true;
    }

    @Override
    public void updateCj(Cj cj, String moneyBeforeModification) {
        String idCard = cj.getIdCard();
        String month = cj.getDate();
        QueryWrapper<Wage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_card",idCard);
        queryWrapper.eq("date",month);
//        type为1是奖励
        if(cj.getType().equals(1)){
            if(baseMapper.selectCount(queryWrapper) == 0){
                Wage wage = new Wage();
                BeanUtils.copyProperties(cj,wage);
                wage.setBonus(cj.getMoney());
                baseMapper.insert(wage);
            }else{
                Wage wage = new Wage();
                wage.setBonus(cj.getMoney());
                BeanUtils.copyProperties(cj,wage);
                QueryWrapper<Wage> wageWrapper = new QueryWrapper<>();
                wageWrapper.eq("id_card",cj.getIdCard());
                wageWrapper.eq("date",cj.getDate());
//                System.out.println(cj.getDate());
//                System.out.println(baseMapper.selectObjs(queryWrapper));
                BigDecimal bd=new BigDecimal(moneyBeforeModification);
                BigDecimal bonusBeforeModify =  baseMapper.selectOne(queryWrapper).getBonus();
                bonusBeforeModify = bonusBeforeModify.subtract(bd);
//                baseMapper.update(wage,wageWrapper);
                BigDecimal bonusAfterModify = bonusBeforeModify.add(cj.getMoney());
                wage.setBonus(bonusAfterModify);
                baseMapper.update(wage,wageWrapper);
            }
        }
        if(cj.getType().equals(0)){
            if(baseMapper.selectCount(queryWrapper) == 0){
                Wage wage = new Wage();
                BeanUtils.copyProperties(cj,wage);
                wage.setFine(cj.getMoney());
                baseMapper.insert(wage);
            }else{
                Wage wage = new Wage();
                wage.setFine(cj.getMoney());
                BeanUtils.copyProperties(cj,wage);
                QueryWrapper<Wage> wageWrapper = new QueryWrapper<>();
                wageWrapper.eq("id_card",cj.getIdCard());
                wageWrapper.eq("date",cj.getDate());
                BigDecimal bd=new BigDecimal(moneyBeforeModification);
                BigDecimal fineBeforeModify =  baseMapper.selectOne(queryWrapper).getFine();
                fineBeforeModify = fineBeforeModify.subtract(bd);
//                baseMapper.update(wage,wageWrapper);
                BigDecimal fineAfterModify = fineBeforeModify.add(cj.getMoney());
                wage.setFine(fineAfterModify);
                baseMapper.update(wage,wageWrapper);
            }
        }
    }

    @Override
    public void updateCjInTypeChange(Cj cj, String moneyBeforeModification) {
        String idCard = cj.getIdCard();
        String month = cj.getDate();
        QueryWrapper<Wage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_card",idCard);
        queryWrapper.eq("date",month);
        if(cj.getType().equals(1)){
            Wage wage = new Wage();
//            wage.setBonus(cj.getMoney());
            BeanUtils.copyProperties(cj,wage);
            QueryWrapper<Wage> wageWrapper = new QueryWrapper<>();
            wageWrapper.eq("id_card",cj.getIdCard());
            wageWrapper.eq("date",cj.getDate());
            BigDecimal bd=new BigDecimal(moneyBeforeModification);

            BigDecimal bonusBeforeModify = baseMapper.selectOne(queryWrapper).getBonus();
//            bonusBeforeModify = bonusBeforeModify.add(bd);
            BigDecimal bonusAfterModify = bonusBeforeModify.add(cj.getMoney());
            wage.setBonus(bonusAfterModify);
            baseMapper.update(wage,wageWrapper);

            BigDecimal fineBeforeModify =  baseMapper.selectOne(queryWrapper).getFine();
            fineBeforeModify = fineBeforeModify.subtract(bd);
//            BigDecimal fineAfterModify = fineBeforeModify.subtract(cj.getMoney());
            wage.setFine(fineBeforeModify);
            baseMapper.update(wage,wageWrapper);
        }
        if(cj.getType().equals(0)){
            Wage wage = new Wage();
//            wage.setBonus(cj.getMoney());
            BeanUtils.copyProperties(cj,wage);
            QueryWrapper<Wage> wageWrapper = new QueryWrapper<>();
            wageWrapper.eq("id_card",cj.getIdCard());
            wageWrapper.eq("date",cj.getDate());
            BigDecimal bd=new BigDecimal(moneyBeforeModification);
            BigDecimal fineBeforeModify = baseMapper.selectOne(queryWrapper).getFine();
//            fineBeforeModify = fineBeforeModify.add(bd);
            BigDecimal fineAfterModify = fineBeforeModify.add(cj.getMoney());
            wage.setFine(fineAfterModify);
            baseMapper.update(wage,wageWrapper);

            BigDecimal bonusBeforeModify = baseMapper.selectOne(queryWrapper).getBonus();
            bonusBeforeModify = bonusBeforeModify.subtract(bd);
//            BigDecimal bonusAfterModify = bonusBeforeModify.subtract(cj.getMoney());
            wage.setBonus(bonusBeforeModify);
            baseMapper.update(wage,wageWrapper);
        }
    }

    @Override
    public void deleteCj(String type, String money, Cj cj) {
        String idCard = cj.getIdCard();
        String month = cj.getDate();
        QueryWrapper<Wage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_card",idCard);
        queryWrapper.eq("date",month);
        Wage wage = new Wage();
        if(type.equals("1")){
            BigDecimal bonus = baseMapper.selectOne(queryWrapper).getBonus();
            bonus = bonus.subtract(cj.getMoney());
            wage.setBonus(bonus);
            baseMapper.update(wage,queryWrapper);
        }
        if(type.equals("0")){
            BigDecimal fine = baseMapper.selectOne(queryWrapper).getFine();
            fine = fine.subtract(cj.getMoney());
            wage.setFine(fine);
            baseMapper.update(wage,queryWrapper);
        }
    }

//    @Override
//    public void storeSelected(Wage wage) {
//        for(Integer i : wageList){
//            if(wage.getId().equals(i))
//                wageList.remove(i);
//            return;
//        }
//        wageList.add(wage.getId());
//        System.out.println(wageList.toString());
//    }
//
//    @Override
//    public List<Integer> takeSelected() {
//        return wageList;
//    }
}
