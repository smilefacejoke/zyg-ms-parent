package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.BrandDao;
import com.zyg.manager.entity.BrandEntity;
import com.zyg.manager.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {


//    1. 条件查询带分页
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        1.1 构造查询条件
        QueryWrapper<BrandEntity> queryWrapper=new QueryWrapper<>();
//        1.2 添加查询条件
//        1.2.1 得到查询条件参数key
        Object key=params.get("key");
        if(key!=null){
            queryWrapper.like("id",key).or()
                    .like("name",key).or()
                    .like("first_char",key);
        }

//        1.3 开始分页带条件查询
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

//        1.4 返回分页信息
        return new PageUtils(page);
    }

}