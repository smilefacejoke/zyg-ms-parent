package com.zyg.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author 54350
 */
@CanalEventListener
@EnableCanalClient
public class CanalEventDataListener {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 监听插入事件
     * @param eventType
     * @param rowData
     */
//    @InsertListenPoint
//    public void onEventInsert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        System.out.println("onEventInsert");
//        System.out.println("------------------------------------------------------------------------------------");
//        rowData.getAfterColumnsList().stream().forEach(c-> System.out.println(c.getName() + "->" + c.getValue()));
//    }

    /**
     * 监听修改事件
     * @param eventType
     * @param rowData
     */
//    @UpdateListenPoint
//    public void onEventUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        System.out.println("onEventUpdate");
//        System.out.println("------------------------------------------------------------------------------------");
//        rowData.getAfterColumnsList().stream().forEach(c-> System.out.println(c.getName() + "->" + c.getValue()));
//
//    }

    /**
     * 监听删除功能
     * @param eventType
     * @param rowData
     */
    @DeleteListenPoint
    public void onEventDelete(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println("onEventDelete");
        System.out.println("------------------------------------------------------------------------------------");
        rowData.getBeforeColumnsList().stream().forEach(c-> System.out.println( c.getName() + "->" + c.getValue()));
    }


    /**
     * 自定义监听
     * @param eventType
     * @param rowData
     */
    @ListenPoint(destination = "example",schema = "zyg_manager",table = {"tb_content"},
            eventType = {
                    CanalEntry.EventType.UPDATE,
                    CanalEntry.EventType.INSERT,
                    CanalEntry.EventType.DELETE}
    )
    public void onEventCustomUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println("从redis删除广告列表。。。");
        redisTemplate.delete("contentList");
    }

}
