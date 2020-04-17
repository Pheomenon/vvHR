package com.gao.hr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.hr.common.R;
import com.gao.hr.entity.Cj;
import com.gao.hr.service.CjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-04-14
 */
@RestController
@CrossOrigin
@RequestMapping("/cj")
public class CjController {
    @Autowired
    private CjService cjService;

    @PutMapping
    public R updateCj(@RequestBody Cj cj) {
        if (cjService.updateById(cj)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping
    public R addCj(@RequestBody Cj cj) {
        if (cjService.save(cj)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/delete/{id}")
    public R deleteCj(@PathVariable Integer id) {
        if (cjService.removeById(id)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/get/{id}")
    public R getCj(@PathVariable Integer id){
        Cj cj = cjService.getById(id);
        return R.ok().data("cj", cj);
    }

    @PostMapping("/search/{current}/{limit}")
    public R pageCjCondition(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody(required = false) Map CjCondition) {
        Page<Cj> cjPage = new Page<>(current, limit);
        QueryWrapper<Cj> wrapper = new QueryWrapper<>();
        if (CjCondition.get("type") != null) {
            wrapper.eq("type", CjCondition.get("type").toString());
        }
        if (CjCondition.get("begin") != null) {
            wrapper.gt("time", CjCondition.get("begin").toString());
        }
        if (CjCondition.get("end") != null) {
            wrapper.le("time", CjCondition.get("end").toString());
        }
        wrapper.orderByDesc("time");
        cjService.page(cjPage, wrapper);
        long total = cjPage.getTotal();
        List<Cj> records = cjPage.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }
}

