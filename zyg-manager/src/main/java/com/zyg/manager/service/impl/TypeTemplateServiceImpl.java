package com.zyg.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.zyg.manager.entity.SpecificationOptionEntity;
import com.zyg.manager.service.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SpecificationOptionService optionService;

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

    /**
     * 查询所有的模板
     * @return
     */
    @Override
    public List<TypeTemplateEntity> findAll() {

        List<TypeTemplateEntity> list=this.list();
        for(TypeTemplateEntity entity:list){
            //第一件事：放入品牌列表
            //2.1 得到模板id
            String id=entity.getId();
            //2.2 得到该模板的品牌列表字符串
            String brandIds=entity.getBrandIds();
            //2.3 将以模板id为key，以品牌列表为值放到redis中
            redisTemplate.boundHashOps("brand").put(id,brandIds);

            //第二件事：放入规格列表
            //2.1 得到规格列表
            String specIds=entity.getSpecIds();
            //2.2 转换List<map>格式
            List<Map> maps = JSON.parseArray(specIds, Map.class);
            //2.3 遍历
            for(Map map:maps){
                Object specId=map.get("id");
                List<SpecificationOptionEntity> options = optionService.list(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id", specId));
                map.put("options",options);
            }
            //2.5 转换为JSON串并放到redis中
            redisTemplate.boundHashOps("specList").put(id,JSON.toJSONString(maps));
        }
        return null;
    }

}