package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.Map;


import com.zyg.shop.entity.group.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.GoodsEntity;
import com.zyg.shop.service.GoodsService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 
 *
 * @author taotao
 * @email handsomezwt@gmail.com
 * @date 2021-11-26 17:17:27
 */
@RestController
@RequestMapping("shop/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:goods:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据商品id查询商品
     */
    @GetMapping("/findById/{id}")
    public R findById(@PathVariable String id){
        Goods goods=goodsService.findById(id);
        return R.ok().put("goods",goods);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("shop:goods:info")
    public R info(@PathVariable("id") String id){
		GoodsEntity goods = goodsService.getById(id);

        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:goods:save")
    public R save(@RequestBody Goods goods){
		goodsService.save(goods);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:goods:update")
    public R update(@RequestBody Goods goods){
		goodsService.update(goods);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:goods:delete")
    public R delete(@RequestBody String[] ids){
		goodsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
