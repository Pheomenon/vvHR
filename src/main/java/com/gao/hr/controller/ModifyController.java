package com.gao.hr.controller;

import com.gao.hr.common.R;
import com.gao.hr.entity.vo.AccountVo;
import com.gao.hr.entity.vo.ModifyAccountVo;
import com.gao.hr.service.AccountService;
import com.gao.hr.service.ModifyService;
import com.gao.hr.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Gao
 * @Date:2020-05-05 16:01
 */
@RestController
@RequestMapping("/modify")
@CrossOrigin
public class ModifyController {
    @Autowired
    private ModifyService modifyService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/msm")
    public R sendMsm(@RequestBody AccountVo accountVo){
        String code = RandomUtil.getSixBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        modifyService.send(param,accountVo);
        return R.ok().message("验证码已发送注意查收");
    }

    @PostMapping("/check")
    public R check(@RequestBody ModifyAccountVo modifyAccountVo){
        boolean flag = accountService.check(modifyAccountVo);
        if(flag)
            return R.ok().message("修改成功");
        else
            return R.error().message("验证码错误");
    }
}