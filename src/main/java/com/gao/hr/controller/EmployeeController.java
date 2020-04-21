package com.gao.hr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.hr.common.R;
import com.gao.hr.entity.Employee;
import com.gao.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-04-14
 */
@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PutMapping
    public R updateEmployee(@RequestBody Employee employee) {
        if (employeeService.updateById(employee)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping
    public R addEmployee(@RequestBody Employee employee) {
        if (employeeService.save(employee)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteEmployee(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/{id}")
    public R getEmployee(@PathVariable Integer id){
        Employee employee = employeeService.getById(id);
        return R.ok().data("employee", employee);
    }

    @PostMapping("/importEmployeeInfo")
    public R importEmployeeInfo(MultipartFile file){
        employeeService.importEmployeeInfo(file,employeeService);
        return R.ok();
    }

    @PostMapping("/search/{current}/{limit}")
    public R pageEmployeeCondition(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody(required = false) Map employeeCondition) {
        Page<Employee> employeePage = new Page<>(current, limit);
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        if (employeeCondition.get("departmentName") != null) {
            wrapper.eq("department_name", employeeCondition.get("departmentName").toString());
        }
        if (employeeCondition.get("begin") != null) {
            wrapper.gt("create_time", employeeCondition.get("begin").toString());
        }
        if (employeeCondition.get("end") != null) {
            wrapper.le("create_time", employeeCondition.get("end").toString());
        }
        wrapper.orderByDesc("create_time");
        employeeService.page(employeePage, wrapper);
        long total = employeePage.getTotal();
        List<Employee> records = employeePage.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }

}

