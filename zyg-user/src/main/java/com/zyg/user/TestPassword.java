package com.zyg.user;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 涛哥
 * @date 2021/12/29
 */
public class TestPassword {
    @Test
    public void test01(){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encode=encoder.encode("123");
        System.out.println("encode = " + encode);
    }
}
