package com.gao.hr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.hr.common.R;
import com.gao.hr.entity.Employee;
import com.gao.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    private HashMap<String, String> recordBeforeModification;

    public EmployeeController(HashMap<String, String> recordBeforeModification) {
        this.recordBeforeModification = recordBeforeModification;
    }

    @PutMapping
    public R updateEmployee(@RequestBody Employee employee) {
        if (!recordBeforeModification.get("id_card").equals(employee.getIdCard())) {
            employeeService.checkExist(employee);
        }
        if (employeeService.updateById(employee)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping
    public R addEmployee(@RequestBody Employee employee) {
        employeeService.checkExist(employee);
        if (employeeService.save(employee)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("/checkEmployeeInDepartment")
    public R checkEmployeeInDepartment(@RequestBody Employee employee){
        System.out.println(employee);
        if(employeeService.checkEmployeeInDepartment(employee)){
            return R.ok();
        }else {
            return R.error().message(employee.getName() + "不在" + employee.getDepartmentName() + "中，请核验后再添加");
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
    public R getEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.getById(id);
        recordBeforeModification.put("id_card", employee.getIdCard());
        return R.ok().data("employee", employee);
    }

    @PostMapping("/{employeeName}")
    public R employeeExist(@PathVariable String employeeName) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name", employeeName);
        if (employeeService.getOne(wrapper) != null) {
            return R.ok().data("level",employeeService.getOne(wrapper).getLevel());
        } else {
            return R.error().message("该员工不存在，请先在员工库中添加员工");
        }
    }

    @PostMapping("/importEmployeeInfo")
    public R importEmployeeInfo(MultipartFile file) {
        employeeService.importEmployeeInfo(file, employeeService);
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

    @PostMapping("/getEmployeeNameUseDepartment/{departmentName}")
    public R getEmployeeNameUseDepartment(@PathVariable String departmentName){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department_name",departmentName);
        List<Employee> list = employeeService.list(queryWrapper);
        return R.ok().data("rows",list);
    }

    @PostMapping("/getEmployeeIdCardUseName/{employeeName}")
    public R getEmployeeIdCardUseName(@PathVariable String employeeName){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",employeeName);
        List<Employee> list = employeeService.list(queryWrapper);
        System.out.println(list);
        return R.ok().data("rows",list);
    }

    @PostMapping("/getLevelUseIdCard/{idCard}")
    public R getLevelUseIdCard(@PathVariable String idCard){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_card",idCard);
        Employee employee = employeeService.getOne(queryWrapper);
        return R.ok().data("idCard",employee);
    }

//    @GetMapping("/getSlimEmployee")
//    public R getSlimEmployee(){
//        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
//        List<Employee> employeeList = employeeService.list(queryWrapper);
//        List<String> departmentList = new ArrayList<>();
//        Map<String,String> result = new HashMap<>();
//        for(Employee e : employeeList){
//            if(!departmentList.contains(e.getDepartmentName())){
//                departmentList.add(e.getDepartmentName());
//            }
//        }
//        for (Employee e : employeeList){
//            if(e.getDepartmentName() != null)
//                result.put(e.getDepartmentName(),e.getName()+"身份证号:"+e.getIdCard());
//            else
//                result.put("未分配部门者",e.getName()+"身份证号:"+e.getIdCard());
//        }
//        return R.ok().data("total",result);
//    }

}

