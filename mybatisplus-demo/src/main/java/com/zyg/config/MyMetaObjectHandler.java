package com.zyg.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author 涛哥
 * @date 2021/11/22
 */

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill");
//        this.strictInsertFill(metaObject,"createTime", LocalDateTime.class,LocalDateTime.now());

//        this.strictInsertFill(metaObject,"createTime",()->LocalDateTime.now(),LocalDateTime.class);
//
//        this.fillStrategy(metaObject,"createTime",LocalDateTime.now());
        this.setFieldValByName("createTime",LocalDateTime.now(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill");
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);

    }
}
