package com.zyg.gateway;

import java.time.ZonedDateTime;

/**
 * @author 涛哥
 * @date 2021/12/23
 */
public class testZonedTime {
    public static void main(String[] args) {
        ZonedDateTime now=ZonedDateTime.now();
        String string=now.toString();
        System.out.println("string = " + string);
    }
}
