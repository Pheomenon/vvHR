package com.gao.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.hr.common.R;
import com.gao.hr.entity.Wage;
import com.gao.hr.service.WageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gao
 * @since 2020-04-14
 */
@RestController
//@CrossOrigin
@RequestMapping("/wage")
public class WageController {
    @Autowired
    private WageService wageService;

    private HashMap<String,String> recordBeforeModification;

    public WageController(HashMap<String, String> recordBeforeModification) {
        this.recordBeforeModification = recordBeforeModification;
    }

    @PutMapping
    public R updateWage(@RequestBody Wage wage) {
        if(!recordBeforeModification.get("name").equals(wage.getName()) || !recordBeforeModification.get("month").equals(wage.getMonth().toString()) || !recordBeforeModification.get("department").equals(wage.getDepartment())) {
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
        recordBeforeModification.put("name",wage.getName());
        recordBeforeModification.put("month",wage.getMonth().toString());
        recordBeforeModification.put("department",wage.getDepartment());
        return R.ok().data("wage", wage);
    }

    @PostMapping("/search/{current}/{limit}")
    public R pageWageCondition(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody(required = false) Map wageCondition) {
        Page<Wage> wagePage = new Page<>(current, limit);
        QueryWrapper<Wage> wrapper = new QueryWrapper<>();
        if (wageCondition.get("name") != null) {
            wrapper.eq("name", wageCondition.get("name").toString());
        }
        wrapper.orderByDesc("id");
        wageService.page(wagePage, wrapper);
        long total = wagePage.getTotal();
        List<Wage> records = wagePage.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }
}

