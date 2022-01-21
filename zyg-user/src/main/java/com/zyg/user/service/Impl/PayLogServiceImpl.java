package com.zyg.user.service.Impl;

import com.zyg.common.entity.TbPayLog;
import com.zyg.user.dao.TbPayLogMapper;
import com.zyg.user.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 涛哥
 * @date 2022/1/6
 */
@Service
public class PayLogServiceImpl implements PayLogService {

    @Autowired
    private TbPayLogMapper payLogMapper;

    @Override
    @Transactional
    public void add(TbPayLog payLog) {
        payLogMapper.insert(payLog);
    }

    @Override
    public TbPayLog findById(String outTradeNo) {
        return payLogMapper.selectById(outTradeNo);
    }

    /**
     * 修改支付日志
     * @param payLog
     */
    @Override
    public void update(TbPayLog payLog) {
        payLogMapper.updateById(payLog);
    }
}
