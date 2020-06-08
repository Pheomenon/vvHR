package com.gao.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.MyException;
import com.gao.hr.entity.Account;
import com.gao.hr.entity.vo.ModifyAccountVo;
import com.gao.hr.mapper.AccountMapper;
import com.gao.hr.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.hr.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gao
 * @since 2020-04-14
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(String username, String password) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        if (baseMapper.selectOne(wrapper.eq("username", username)) != null){
            if(encoder.matches(password, baseMapper.selectOne(wrapper).getPassword()))
                return jwtUtil.createJWT(username,"adminG");
            else throw new MyException(20010,"用户名或密码错误");
        }
        else throw new MyException(20011,"用户名或密码错误");
    }

    @Override
    public boolean check(ModifyAccountVo modifyAccountVo) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username", modifyAccountVo.getUsername());
        Account account = baseMapper.selectOne(wrapper);
        if (account != null) {
            if (modifyAccountVo.getCode().equals(redisTemplate.opsForValue().get(account.getTel()))) {
                account.setPassword(encoder.encode(modifyAccountVo.getPassword()));
                baseMapper.updateById(account);
                return true;
            } else throw new MyException(20003, "验证码错误");
        } else {
            throw new MyException(20007, "用户名或密码错误");
        }
    }
}
