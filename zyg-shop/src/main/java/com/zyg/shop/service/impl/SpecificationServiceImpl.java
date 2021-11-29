package com.zyg.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.zyg.shop.entity.SpecificationOptionEntity;
import com.zyg.shop.entity.TypeTemplateEntity;
import com.zyg.shop.entity.group.Specification;
import com.zyg.shop.service.SpecificationOptionService;
import com.zyg.shop.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.shop.dao.SpecificationDao;
import com.zyg.shop.entity.SpecificationEntity;
import com.zyg.shop.service.SpecificationService;


@Service("specificationService")
public class SpecificationServiceImpl extends ServiceImpl<SpecificationDao, SpecificationEntity> implements SpecificationService {

    @Autowired
    private TypeTemplateService typeTemplateService;
    @Autowired
    private SpecificationOptionService specificationOptionService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpecificationEntity> page = this.page(
                new Query<SpecificationEntity>().getPage(params),
                new QueryWrapper<SpecificationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map> findSpecByTypeId(String typeId) {
        //2.1 先查询出模板对象
        TypeTemplateEntity templateEntity= typeTemplateService.getById(typeId);
        //2.2 得到模板中的规格列表
        String specIds=templateEntity.getSpecIds();
        //2.3 转换为java中的list
        List<Map> maps = JSON.parseArray(specIds, Map.class);
        //2.4 遍历集合，为每隔map添加一个option对象
        for(Map map:maps){
            //2.4.1 得到规格id
            Integer id=(Integer) map.get("id");
            //2.4.2 根据规格id查询出对应的规格选项列表
            List<SpecificationOptionEntity> optionEntities = specificationOptionService.list(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id", id));
            //2.4.3 为map赋值
            map.put("options",optionEntities);

        }
        return maps;
    }

}