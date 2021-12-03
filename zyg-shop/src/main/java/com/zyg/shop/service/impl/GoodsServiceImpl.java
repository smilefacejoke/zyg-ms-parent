package com.zyg.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.zyg.shop.entity.GoodsDescEntity;
import com.zyg.shop.entity.ItemEntity;
import com.zyg.shop.entity.group.Goods;
import com.zyg.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.shop.dao.GoodsDao;
import com.zyg.shop.entity.GoodsEntity;
import org.springframework.transaction.annotation.Transactional;


@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Autowired
    private GoodsDescService goodsDescService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ItemCatService itemCatService;
    @Autowired
    private SellerService sellerService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsEntity> page = this.page(
                new Query<GoodsEntity>().getPage(params),
                new QueryWrapper<GoodsEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 保存商品
     * @param goods
     */
    //1. 保存商品
    @Override
    @Transactional
    public void save(Goods goods) {
        //1.1 保存GoodsEntity
        goods.getGoods().setAuditStatus("0");   //0:未审核 1：审核通过
        goods.getGoods().setSellerId("zwt");  //商家id
        this.save(goods.getGoods());

        //1.2 保存goodsDescEntity
        goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
        goodsDescService.save(goods.getGoodsDesc());

        //1.3 添加sku商品列表
        saveItem(goods);
    }


    //2. 保存sku商品列表
    private void saveItem(Goods goods) {
        List<ItemEntity> items = goods.getItems();
        for (ItemEntity item : items) {
            //1.3.1 设置商品id
            item.setGoodsId(goods.getGoods().getId());
            item.setCreateTime(new Date());
            item.setCategoryid(goods.getGoods().getCategory3Id());
            //1.3.2 设置分类
            item.setCategory(itemCatService.getById(goods.getGoods().getCategory3Id()).getName());
            //1.3.2 设置品牌名称
            item.setBrand(brandService.getById(goods.getGoods().getBrandId()).getName());

            //1.3.3 设置图像
            String itemImages = goods.getGoodsDesc().getItemImages();
            List<Map> maps = JSON.parseArray(itemImages, Map.class);
            if(maps != null && maps.size() > 0) {
                item.setImage(maps.get(0).get("url") + "");
            }
            //1.3.4 设置商家id
            item.setSellerId(goods.getGoods().getSellerId());
            //1.3.5 设置商家名称
            String nickName = sellerService.getById(goods.getGoods().getSellerId()).getNickName();
            item.setSeller(nickName);
            //1.3.6 设置商家标题名称
            item.setTitle(goods.getGoods().getGoodsName());
            //1.3.7 设置商品状态值
            item.setStatus("1");
            //1.3.8 设置修改时间
            item.setUpdateTime(new Date());
            //1.3.9 添加item
            itemService.save(item);
        }
    }


    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @Override
    public Goods findById(String id) {
        //1. 根据商品id查询商品
        GoodsEntity goodsEntity = this.getById(id);
        //2. 根据商品id查询商品描述对象
        GoodsDescEntity goodsDescEntity=goodsDescService.getById(id);
        //3. 根据外键查询
        List<ItemEntity> itemEntities = itemService.list(new QueryWrapper<ItemEntity>().eq("goods_id", id));
        //4. 构造组合对象
        Goods goods=new Goods();
        goods.setGoods(goodsEntity);
        goods.setGoodsDesc(goodsDescEntity);
        goods.setItems(itemEntities);
        //5. 返回
        return goods;
    }

    /**
     * 修改商品
     * @param goods
     */
    @Override
    @Transactional
    public void update(Goods goods) {
        //3.1 修改商品表
        this.updateById(goods.getGoods());
        //3.2 修改商品描述表
        //3.2.1 设置外键关系
        goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
        //3.2.2 修改商品描述表
        goodsDescService.updateById(goods.getGoodsDesc());

        //3.3 修改sku商品列表
        //3.3.1 根据goodsid删除sku列表
        itemService.remove(new QueryWrapper<ItemEntity>().eq("goods_id",goods.getGoods().getId()));
        //3.3.2 再添加商品列表
        saveItem(goods);
    }

}