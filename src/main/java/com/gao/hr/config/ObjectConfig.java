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
    public static final ThreadLocal<Date> dateThreadLocal = ThreadLocal.withInitial(Date::new);

    @Override
    public void insertFill(MetaObject metaObject) {

//        this.setFieldValByName("departmentName","未分配",metaObject);
//        this.setFieldValByName("portraitUrl","https://edu-102.oss-cn-beijing.aliyuncs.com/defaultUserIcon.jpg",metaObject);
        this.setFieldValByName("isHire",0,metaObject);
        this.setFieldValByName("time",dateThreadLocal.get(),metaObject);
        this.setFieldValByName("createTime",dateThreadLocal.get(),metaObject);
        this.setFieldValByName("isDeleted",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",dateThreadLocal.get(),metaObject);
    }
}
