package com.gao.hr.service;

import com.gao.hr.entity.vo.AccountVo;

import java.util.Map;

/**
 * @Author:Gao
 * @Date:2020-05-05 16:02
 */
public interface ModifyService {
    boolean send(Map<String, Object> param, AccountVo accountVo);
}
