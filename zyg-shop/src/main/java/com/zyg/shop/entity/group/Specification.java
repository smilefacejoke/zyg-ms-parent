package com.zyg.shop.entity.group;

import com.zyg.shop.entity.SpecificationEntity;
import com.zyg.shop.entity.SpecificationOptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 涛哥
 * @date 2021/11/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specification implements Serializable {

    private SpecificationEntity spec;
    private List<SpecificationOptionEntity> options;

}
