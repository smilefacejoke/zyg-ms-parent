package com.zyg.order.service;

import com.alipay.api.AlipayApiException;
import com.zyg.common.entity.PayAsyncVo;

import java.util.Map;

/**
 * @author 涛哥
 * @date 2022/1/21
 */
public interface PayService {

    String validSignature(PayAsyncVo vo, Map<String, String> params) throws AlipayApiException;
}
