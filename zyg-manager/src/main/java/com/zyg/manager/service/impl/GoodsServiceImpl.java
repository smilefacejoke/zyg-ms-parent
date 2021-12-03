package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.GoodsDao;
import com.zyg.manager.entity.GoodsEntity;
import com.zyg.manager.service.GoodsService;


@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsEntity> page = this.page(
                new Query<GoodsEntity>().getPage(params),
                new QueryWrapper<GoodsEntity>().eq("audit_status",0)
        );

        return new PageUtils(page);
    }

    /**
     * 商品审核
     * @param status
     * @param ids
     */
    @Override
    public void updateStatus(String status, String[] ids) {
        //1 根据商品id
        for(String id:ids){
            GoodsEntity goodsEntity=this.getById(id);
            goodsEntity.setAuditStatus(status);
            this.updateById(goodsEntity);
        }

    }

}