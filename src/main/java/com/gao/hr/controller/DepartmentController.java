package com.gao.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.MyException;
import com.gao.hr.common.R;
import com.gao.hr.entity.Department;
import com.gao.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author gao
 * @since 2020-04-14
 */
@RestController
@CrossOrigin
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    private HashMap<String,String> recordBeforeModification;

    public DepartmentController(HashMap<String,String> recordBeforeModification){
        this.recordBeforeModification = recordBeforeModification;
    }

    @GetMapping
    public R getDepartment() {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        List<Department> list = departmentService.list(wrapper);
        Map map = new HashMap();
        map.put("total", list.size());
        map.put("rows", list);
        return R.ok().data(map);
    }

    @GetMapping("/{id}")
    public R getCj(@PathVariable Integer id){
        Department department = departmentService.getById(id);
        recordBeforeModification.put("name",department.getName());
        return R.ok().data("department", department);
    }

    @PutMapping()
    public R updateDepartment(@RequestBody Department department) {
        if(!recordBeforeModification.get("name").equals(department.getName())){
            departmentService.checkExist(department);
        }
        if (departmentService.updateById(department)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteDepartment(@PathVariable Integer id) {
        if (departmentService.removeById(id)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping()
    public R addDepartment(@RequestBody Department department) {
        departmentService.checkExist(department);
        if (departmentService.save(department)) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

