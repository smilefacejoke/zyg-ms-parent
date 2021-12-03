package com.zyg.shop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zyg.shop.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.shop.entity.ItemCatEntity;
import com.zyg.shop.service.ItemCatService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 商品类目
 *
 * @author taotao
 * @email handsomezwt@gmail.com
 * @date 2021-11-26 17:17:28
 */
@RestController
@RequestMapping("/shop/itemCat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("shop:itemcat:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemCatService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 查询所有分类不分页
     */
    @GetMapping("/findItemCat")
    public R findItemCat(){
        return R.ok().put("itemCatList",itemCatService.list());
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("shop:itemcat:info")
    public R info(@PathVariable("id") String id){
		ItemCatEntity itemCat = itemCatService.getById(id);

        return R.ok().put("itemCat", itemCat);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("shop:itemcat:save")
    public R save(@RequestBody ItemCatEntity itemCat){
		itemCatService.save(itemCat);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("shop:itemcat:update")
    public R update(@RequestBody ItemCatEntity itemCat){
		itemCatService.updateById(itemCat);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("shop:itemcat:delete")
    public R delete(@RequestBody String[] ids){
		itemCatService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 查询所有分类(一级分类)
     */
    @GetMapping("/findAll")
    public R findAll(){
        List<ItemCatEntity> collect=itemCatService.list()
                .stream().filter(f-> "0".equals( f.getParentId())).collect(Collectors.toList());
        return R.ok().put("categorys1",collect);
    }

    /**
     * 根据父id查询此分类下的所有子分类列表
     */
    @GetMapping("/findByParentId/{itemCatId}")
    public R findByParentId(@PathVariable String itemCatId){
        List<ItemCatEntity> itemCatEntities=itemCatService.findByParentId(itemCatId);
        return R.ok().put("list",itemCatEntities);
    }

}
