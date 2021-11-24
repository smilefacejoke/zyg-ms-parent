package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.ContentEntity;
import com.zyg.manager.service.ContentService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 广告
 *
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
@RestController
@RequestMapping("manager/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:content:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = contentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:content:info")
    public R info(@PathVariable("id") String id){
		ContentEntity content = contentService.getById(id);

        return R.ok().put("content", content);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:content:save")
    public R save(@RequestBody ContentEntity content){
		contentService.save(content);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:content:update")
    public R update(@RequestBody ContentEntity content){
		contentService.updateById(content);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:content:delete")
    public R delete(@RequestBody String[] ids){
		contentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
