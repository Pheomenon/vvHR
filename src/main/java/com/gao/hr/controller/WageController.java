package com.gao.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.hr.common.R;
import com.gao.hr.entity.Employee;
import com.gao.hr.entity.Wage;
import com.gao.hr.entity.vo.WageVo;
import com.gao.hr.service.EmployeeService;
import com.gao.hr.service.WageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gao
 * @since 2020-04-14
 */
@RestController
@CrossOrigin
@RequestMapping("/wage")
@Slf4j
public class WageController {
    @Autowired
    private WageService wageService;

    @Autowired
    private EmployeeService employeeService;

    private HashMap<String,String> recordBeforeModification;

    public WageController(HashMap<String, String> recordBeforeModification) {
        this.recordBeforeModification = recordBeforeModification;
    }

    @PutMapping
    public R updateWage(@RequestBody Wage wage) {
        //如果父本和传进来的副本不同，则进行重复检测。
        if(!recordBeforeModification.get("name").equals(wage.getName()) || !recordBeforeModification.get("date").equals(wage.getDate()) || !recordBeforeModification.get("department_name").equals(wage.getDepartmentName())) {
            wageService.checkRecordExist(wage);
        }
        if (wageService.updateById(wage)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping
    public R addWage(@RequestBody Wage wage) {
        wageService.checkRecordExist(wage);
        if (wageService.save(wage)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteWage(@PathVariable Integer id) {
        if (wageService.removeById(id)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/{id}")
    public R getWage(@PathVariable Integer id){
        Wage wage = wageService.getById(id);
//        System.out.println(wage);
        recordBeforeModification.put("name",wage.getName());
        recordBeforeModification.put("department_name",wage.getDepartmentName());
        recordBeforeModification.put("date",wage.getDate());

        QueryWrapper<Wage> wageQueryWrapper = new QueryWrapper<>();
        wageQueryWrapper.eq("id_card",wage.getIdCard());
        wageService.getOne(wageQueryWrapper);

        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.eq("id_card",wage.getIdCard());
        Employee employee = employeeService.getOne(employeeQueryWrapper);
//        log.error(employee.toString());
        WageVo wageVo = new WageVo();
        BeanUtils.copyProperties(wage,wageVo);
//        log.error(wageVo.toString());
        wageVo.setIdCard(employee.getIdCard());
        wageVo.setLevel(employee.getLevel());
        //这个VO的id是Wage的id
//        log.error(wageVo.toString());
        return R.ok().data("wage", wageVo);
    }

    @PostMapping("/search/{current}/{limit}")
    public R pageWageCondition(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody(required = false) Map wageCondition) {
        Page<Wage> wagePage = new Page<>(current, limit);
        Page<Employee> employeePage = new Page<>(current, 1000);
        QueryWrapper<Wage> wrapper = new QueryWrapper<>();
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        if (wageCondition.get("name") != null) {
            wrapper.eq("name", wageCondition.get("name").toString());
            employeeQueryWrapper.eq("name",wageCondition.get("name").toString());
        }
        wrapper.orderByDesc("id");
        wageService.page(wagePage, wrapper);
        long total = wagePage.getTotal();
        List<WageVo> records = new ArrayList<>();
        for (Wage wage : wagePage.getRecords()) {
            WageVo wageVo = new WageVo();
            BeanUtils.copyProperties(wage,wageVo);
            wageVo.setWageId(wage.getId());
            records.add(wageVo);
        }

        employeeService.page(employeePage,employeeQueryWrapper);
        List<Employee> employeeList = employeePage.getRecords();
        for (Employee employee : employeeList) {
            for (WageVo record : records) {
                if (employee.getName().equals(record.getName()) && employee.getDepartmentName().equals(record.getDepartmentName())) {
//                    log.info(employee.getName()+"  "+employee.getIdCard());
                    record.setIdCard(employee.getIdCard());
                    record.setId(employee.getId());
                    record.setLevel(employee.getLevel());
                }
            }
        }
        Map map = new HashMap();
        map.put("total", total);
//        System.out.println(records);
        map.put("rows", records);
        return R.ok().data(map);
    }

//    @PostMapping("/batchOperate")
//    public R batchStore(@RequestBody Wage wage){
//        wageService.storeSelected(wage);
//        return R.ok();
//    }
//
//    @GetMapping("/batchOperate")
//    public R batchTake(){
//        List<Integer> wageList = wageService.takeSelected();
//        return R.ok().data("selected",wageList);
//    }

}

