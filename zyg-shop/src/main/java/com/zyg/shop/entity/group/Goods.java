package com.zyg.shop.entity.group;

import com.zyg.shop.entity.GoodsDescEntity;
import com.zyg.shop.entity.GoodsEntity;
import com.zyg.shop.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 涛哥
 * @date 2021/11/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    /**
     * spu
     */
    private GoodsEntity goods;


    /**
     * spu商品描述
     */
    private GoodsDescEntity goodsDesc;

    /**
     * sku商品列表
     */
    private List<ItemEntity> items;

}
