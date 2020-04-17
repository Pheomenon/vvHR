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
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","C:\\Users\\Gao\\Desktop\\20150810224146_zJEhY.jpeg");
    }
}
