package com.gao.hr.controller;

import com.gao.hr.common.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Gao
 * @Date:2020-04-13 19:47
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    //login
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://edu-102.oss-cn-beijing.aliyuncs.com/2020/04/17/3ff94ad478434ef8883147a6ecacaa801.jpeg");
    }
}
