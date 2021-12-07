package com.zyg.manager.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.ContentDao;
import com.zyg.manager.entity.ContentEntity;
import com.zyg.manager.service.ContentService;


@Service("contentService")
public class ContentServiceImpl extends ServiceImpl<ContentDao, ContentEntity> implements ContentService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContentEntity> page = this.page(
                new Query<ContentEntity>().getPage(params),
                new QueryWrapper<ContentEntity>()
        );

        return new PageUtils(page);
    }

    //2. 从缓存中得到图片，第一次没有就从数据库中取出
    @Override
    public List<ContentEntity> findAll() {
        List<ContentEntity> contentEntities=null;
        //2.1 从redis中得到广告列表
        //2.2 得到广告列表的字符串
        String contentList = redisTemplate.opsForValue().get("contentList");
        //2.3 转换为字符串
        if(StringUtils.isNotBlank(contentList)){
            //数据不为空则加入redis缓存中获取
            contentEntities= JSON.parseArray(contentList,ContentEntity.class);
        }else {
            //如果没有数据则进入数据库中获取
            List<ContentEntity> list = this.list();
            //2.5 放到redis中
            redisTemplate.opsForValue().set("contentList",JSON.toJSONString(list));
        }
        //2.6 返回数据
        return contentEntities;
    }

}