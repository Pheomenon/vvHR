package com.gao.hr.service;

import com.gao.hr.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.hr.entity.vo.ModifyAccountVo;

/**
 *
 * @author gao
 * @since 2020-04-14
 */
public interface AccountService extends IService<Account> {

    String login(String username, String password);

    boolean check(ModifyAccountVo modifyAccountVo);
}
