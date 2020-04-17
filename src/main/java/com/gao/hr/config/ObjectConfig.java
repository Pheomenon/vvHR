package com.gao.hr.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author:Gao
 * @Date:2020-04-14 21:24
 */
@Component
public class ObjectConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("time",new Date(),metaObject);
        this.setFieldValByName("isDeleted",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
