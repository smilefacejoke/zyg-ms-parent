package com.zyg.user.service;

import com.zyg.common.entity.TbPayLog;

/**
 * @author 涛哥
 * @date 2022/1/6
 */
public interface PayLogService {
    void update(TbPayLog payLog);

    void add(TbPayLog payLog);

    TbPayLog findById(String outTradeNo);
}
