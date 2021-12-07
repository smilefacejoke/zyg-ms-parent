package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.ContentCategoryEntity;
import com.zyg.manager.service.ContentCategoryService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 内容分类
 *
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
@RestController
@RequestMapping("manager/contentcategory")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:contentcategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = contentCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }

    //查询所有分类（不分页）
    @GetMapping("/findAll")
    public R findAll(){
        return R.ok().put("contentCategorys",contentCategoryService.list());
    }



    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:contentcategory:info")
    public R info(@PathVariable("id") String id){
		ContentCategoryEntity contentCategory = contentCategoryService.getById(id);

        return R.ok().put("contentCategory", contentCategory);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:contentcategory:save")
    public R save(@RequestBody ContentCategoryEntity contentCategory){
		contentCategoryService.save(contentCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:contentcategory:update")
    public R update(@RequestBody ContentCategoryEntity contentCategory){
		contentCategoryService.updateById(contentCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:contentcategory:delete")
    public R delete(@RequestBody String[] ids){
		contentCategoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
