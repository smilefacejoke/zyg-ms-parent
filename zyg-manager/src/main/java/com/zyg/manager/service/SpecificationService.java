package com.zyg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.manager.entity.SpecificationEntity;
import com.zyg.manager.entity.vo.Specification;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
public interface SpecificationService extends IService<SpecificationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(Specification specification);

    Specification findById(String id);

    void update(Specification specification);

    void delete(List<String> ids);
}

