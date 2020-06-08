package com.gao.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.R;
import com.gao.hr.entity.Account;
import com.gao.hr.entity.vo.AccountVo;
import com.gao.hr.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Gao
 * @Date:2020-04-13 19:47
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AccountService accountService;

    @PostMapping("/registry")
    public R registry(@RequestBody Account account){
        account.setPassword(encoder.encode(account.getPassword()));
        accountService.save(account);
        return R.ok();
    }

    //login
    @PostMapping("/login")
    public R login(@RequestBody AccountVo accountVo){
        String token = accountService.login(accountVo.getUsername(),accountVo.getPassword());
        return R.ok().data("token",token);
    }

    //info
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://edu-102.oss-cn-beijing.aliyuncs.com/2020/04/17/3ff94ad478434ef8883147a6ecacaa801.jpeg");
    }
}
