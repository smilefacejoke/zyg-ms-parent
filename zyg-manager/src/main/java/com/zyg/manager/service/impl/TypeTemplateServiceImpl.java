package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.TypeTemplateDao;
import com.zyg.manager.entity.TypeTemplateEntity;
import com.zyg.manager.service.TypeTemplateService;


@Service("typeTemplateService")
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateDao, TypeTemplateEntity> implements TypeTemplateService {

    //1. 分页带条件查询
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //1.1 定义查询的包装器对象
        QueryWrapper<TypeTemplateEntity> wrapper=new QueryWrapper<>();
        //1.2 封装查询条件
        //1.2.1 得到查询参数
        Object key=params.get("key");
        //1.2.2 包装条件
        if(key!=null){
            wrapper.like("id",key).or()
                    .like("name",key);
        }
        //1.3 开始分页带条件查询
        IPage<TypeTemplateEntity> page = this.page(
                new Query<TypeTemplateEntity>().getPage(params),
                wrapper
        );
        //1.4 返回查询结果
        return new PageUtils(page);
    }

}