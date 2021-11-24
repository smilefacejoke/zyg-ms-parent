package com.zyg.manager.entity.vo;

import com.zyg.manager.entity.SpecificationEntity;
import com.zyg.manager.entity.SpecificationOptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 涛哥
 * @date 2021/11/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specification implements Serializable {
    /**
     * 规格
     */
    private SpecificationEntity spec;

    /**
     * 规格对应的规格选项
     */
    private List<SpecificationOptionEntity> options;

}
