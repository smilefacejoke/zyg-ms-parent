package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.SellerDao;
import com.zyg.manager.entity.SellerEntity;
import com.zyg.manager.service.SellerService;


@Service("sellerService")
public class SellerServiceImpl extends ServiceImpl<SellerDao, SellerEntity> implements SellerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SellerEntity> page = this.page(
                new Query<SellerEntity>().getPage(params),
                new QueryWrapper<SellerEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 商家审核
     * @param sellerId
     * @param status
     */
    @Override
    public void updateStatus(String sellerId, String status) {
        //2.1 根据商家的sellerid查出商家
        SellerEntity sellerEntity=this.getById(sellerId);
        //2.2 修改状态
        sellerEntity.setStatus(status);
        //2.3 开始修改
        this.updateById(sellerEntity);
    }

}