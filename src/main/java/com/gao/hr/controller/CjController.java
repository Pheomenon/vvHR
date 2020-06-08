package com.gao.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.hr.common.R;
import com.gao.hr.entity.Cj;
import com.gao.hr.service.CjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
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

    @DeleteMapping("/{id}")
    public R deleteCj(@PathVariable Integer id) {
        if (cjService.removeById(id)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/{id}")
    public R getCj(@PathVariable Integer id){
        Cj cj = cjService.getById(id);
        return R.ok().data("cj", cj);
    }

    @PostMapping("/search/{current}/{limit}")
    public R pageCjCondition(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody(required = false) Map cjCondition) {
        Page<Cj> cjPage = new Page<>(current, limit);
        QueryWrapper<Cj> wrapper = new QueryWrapper<>();
        if (cjCondition.get("type") != null) {
            wrapper.eq("type", cjCondition.get("type").toString());
        }
        if (cjCondition.get("begin") != null) {
            wrapper.gt("time", cjCondition.get("begin").toString());
        }
        if (cjCondition.get("end") != null) {
            wrapper.le("time", cjCondition.get("end").toString());
        }
        wrapper.orderByDesc("time");
        cjService.page(cjPage, wrapper);
        long total = cjPage.getTotal();
        List<Cj> records = cjPage.getRecords();
        Map<String,Object> map = Collections.synchronizedMap(new HashMap<>());
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }
}

