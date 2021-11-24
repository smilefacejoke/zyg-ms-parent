package com.zyg.manager.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zyg.manager.entity.TypeTemplateEntity;
import com.zyg.manager.service.TypeTemplateService;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.R;



/**
 * 
 *
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
@RestController
@RequestMapping("manager/typetemplate")
public class TypeTemplateController {
    @Autowired
    private TypeTemplateService typeTemplateService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("manager:typetemplate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = typeTemplateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("manager:typetemplate:info")
    public R info(@PathVariable("id") String id){
		TypeTemplateEntity typeTemplate = typeTemplateService.getById(id);

        return R.ok().put("typeTemplate", typeTemplate);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("manager:typetemplate:save")
    public R save(@RequestBody TypeTemplateEntity typeTemplate){
		typeTemplateService.save(typeTemplate);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    //@RequiresPermissions("manager:typetemplate:update")
    public R update(@RequestBody TypeTemplateEntity typeTemplate){
		typeTemplateService.updateById(typeTemplate);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("manager:typetemplate:delete")
    public R delete(@RequestBody String[] ids){
		typeTemplateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
