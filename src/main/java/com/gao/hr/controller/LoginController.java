package com.gao.hr.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.R;
import com.gao.hr.entity.Manager;
import com.gao.hr.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author:Gao
 * @Date:2020-04-13 19:47
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ManagerService managerService;

    @PostMapping("/registry")
    public R registry(@RequestBody Manager manager){
        manager.setPassword(encoder.encode(manager.getPassword()));
        managerService.save(manager);
        return R.ok();
    }

    //login
    @PostMapping("/login")
    public R login(){
//        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
//        wrapper.eq("account",manager.getAccount());
//        Boolean flag = false;
//        if(managerService.getObj(wrapper) != null){
//             flag = encoder.matches(manager.getPassword(),managerService.getOne(wrapper).getPassword());
//        }
//        if(flag)
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://edu-102.oss-cn-beijing.aliyuncs.com/2020/04/17/3ff94ad478434ef8883147a6ecacaa801.jpeg");
    }
}
