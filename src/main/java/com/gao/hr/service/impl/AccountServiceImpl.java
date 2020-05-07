package com.gao.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.MyException;
import com.gao.hr.entity.Account;
import com.gao.hr.entity.vo.ModifyAccountVo;
import com.gao.hr.mapper.AccountMapper;
import com.gao.hr.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gao
 * @since 2020-04-14
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean login(String username, String password) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        if(baseMapper.selectOne(wrapper.eq("username",username)) != null)
            return encoder.matches(password,baseMapper.selectOne(wrapper).getPassword());
        else
            return false;
    }

    @Override
    public boolean check(ModifyAccountVo modifyAccountVo) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username",modifyAccountVo.getUsername());
        Account account = baseMapper.selectOne(wrapper);
        if(account != null){
            if(modifyAccountVo.getCode().equals(redisTemplate.opsForValue().get(account.getTel()))){
                account.setPassword(encoder.encode(modifyAccountVo.getPassword()));
                baseMapper.updateById(account);
                return true;
            }
            else throw new MyException(20003,"验证码错误");
        }
        else {
            throw new MyException(20007,"用户名或密码错误");
        }
    }
}
