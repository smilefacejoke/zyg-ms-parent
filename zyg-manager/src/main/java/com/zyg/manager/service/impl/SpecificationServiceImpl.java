package com.zyg.manager.service.impl;

import com.zyg.manager.entity.SpecificationOptionEntity;
import com.zyg.manager.entity.vo.Specification;
import com.zyg.manager.service.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.SpecificationDao;
import com.zyg.manager.entity.SpecificationEntity;
import com.zyg.manager.service.SpecificationService;


@Service("specificationService")
public class SpecificationServiceImpl extends ServiceImpl<SpecificationDao, SpecificationEntity> implements SpecificationService {

    @Autowired
    private SpecificationOptionService optionService;

    /**
     * 列表规格
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        1.1 定义查询包装器对象
        QueryWrapper<SpecificationEntity> queryWrapper=new QueryWrapper<>();
//        1.2 封装查询条件
        Object key=params.get("key");
        if(key!=null){
            queryWrapper.like("id",key).or()
                    .like("spec_name",key).or();
        }
//        1.3 开始查询
        IPage<SpecificationEntity> page = this.page(
                new Query<SpecificationEntity>().getPage(params),
                queryWrapper
        );
//          1.4 返回查询结构
        return new PageUtils(page);
    }

    /**
     * 添加规格
     * @param specification
     */
    @Override
    public void save(Specification specification) {
//        2.1 添加规格
        this.save(specification.getSpec());
//        2.2 添加规格选项
//        2.2.1 得到规格id
        String id=specification.getSpec().getId();
//        2.2.2 为每一个规格选项设置对应的规格id
        for(SpecificationOptionEntity option:specification.getOptions()){
            // 为规格选项设置对应id
            option.setSpecId(id);
            // 添加规格选项
            optionService.save(option);
        }
    }

    /**
     * 根据id查询规格及规格选项
     * @param id
     * @return
     */
    @Override
    public Specification findById(String id) {
//        3.1 查询规格数据
        SpecificationEntity specificationEntity=this.getById(id);
//        3.2 根据规格查询规格选项列表
        List<SpecificationOptionEntity> options = optionService.list(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id", id));
//        3.3 定义组合类
        Specification specification=new Specification();
        specification.setSpec(specificationEntity);
        specification.setOptions(options);

        return specification;


    }

    /**
     * 修改规格
     * @param specification
     */
    @Override
    public void update(Specification specification) {
//        4.1 修改规格
        this.updateById(specification.getSpec());
//        4.2 根据规格id从多方表中删除规格选项数据
        optionService.remove(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id",specification.getSpec().getId()));
//        4.3 再添加规格列表选项
        for(SpecificationOptionEntity optionEntity:specification.getOptions()){
//            4.3.1 关联外键
            optionEntity.setSpecId(specification.getSpec().getId());
//            4.3.2 再添加规格选项
            optionService.save(optionEntity);
        }


    }

    /**
     * 根据id删除
     * @param ids
     */
    @Override
    public void delete(List<String> ids) {
        for(String id:ids){
//            5.1 删除规格
            this.removeById(id);
//            5.2 删除规格选项
            optionService.remove(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id",id));
        }

    }
}