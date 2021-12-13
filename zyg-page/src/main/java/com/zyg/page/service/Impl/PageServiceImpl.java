package com.zyg.page.service.Impl;

import com.zyg.page.dao.GoodsDao;
import com.zyg.page.entity.GoodsDescEntity;
import com.zyg.page.entity.GoodsEntity;
import com.zyg.page.service.GoodsDescService;
import com.zyg.page.service.GoodsService;
import com.zyg.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 涛哥
 * @date 2021/12/10
 */
@Service
public class PageServiceImpl implements PageService {

    //引入the\ymeleaf模板引擎
    @Autowired
    private TemplateEngine engine;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsDescService goodsDescService;

    //id: 代表spu商品id
    @Override
    public void createHtml(Long id) throws IOException {

        //1.1 定义模板工作的上下文对象
        Context context=new Context();
        //1.2 定义上下文环境关联的数据集合
        Map<String,Object> dataMap=new HashMap<>();

        //1.4 查询spu商品对象
        GoodsEntity goodsEntity=goodsService.getById(id);
        //1.5 将商品放入datamap中
        dataMap.put("goods",goodsEntity);

        //1.6 根据id查询商品描述对象
        GoodsDescEntity goodsDescEntity=goodsDescService.getById(id);
        //1.7 放到map集合中
        dataMap.put("goodsDesc",goodsDescEntity);

        //1.8 构造输出流对象，指定输出的静态页面的位置
        Writer writer=new FileWriter("E:\\nginx-1.8.0\\html\\item\\"+id+".html");

        //1.9 绑定聚合到上下文对象中
        context.setVariables(dataMap);

        //参数1：代表模板的视图名， 参数2：模板工作的上下文环境， 参数3：指定输出的静态页面的输出流
        engine.process("item",context,writer);
    }
}
