package com.zyg.search.service;

import com.zyg.search.entity.ItemVo;

import java.util.Map;

/**
 * @author 涛哥
 * @date 2021/12/6
 */
public interface ItemSearchService {
    Map<String, Object> search(ItemVo vo);
}
