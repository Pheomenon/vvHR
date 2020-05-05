package com.gao.hr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gao.hr.common.MyException;
import com.gao.hr.entity.Account;
import com.gao.hr.entity.vo.AccountVo;
import com.gao.hr.mapper.AccountMapper;
import com.gao.hr.service.ModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Gao
 * @Date:2020-05-05 16:02
 */
@Service
public class ModifyServiceImpl implements ModifyService {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean send(Map<String, Object> param, AccountVo accountVo) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username",accountVo.getUsername());
        Account result = accountMapper.selectOne(wrapper);
        if(result == null){
            throw new MyException(20001,"用户名有误");
        }
        String tel = result.getTel();

        if(!encoder.matches(accountVo.getPassword(),accountMapper.selectOne(wrapper).getPassword())){
            throw new MyException(20001,"密码错误");
        }

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4GFBgPprPqBTC7gc5NTE", "yNrZsSpHNqgw5Qa7iiW1uAa6HGy5iH");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers",tel);
        request.putQueryParameter("SignName", "XonLab");
        request.putQueryParameter("TemplateCode", "SMS_189245766");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            //发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            redisTemplate.opsForValue().set(tel,(String)param.get("code"),5, TimeUnit.MINUTES);
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
            throw new MyException(20001,"验证码发送失败");
        }
    }
}
