package com.fs.service.test;

import com.fs.service.TeaServiceSupport;
import com.fs.po.Tea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml","classpath:config/spring-service.xml"})
public class TeaServiceTest {
    @Autowired
    private TeaServiceSupport service;

    @Test
    public void getStuByNumTest(){
        Tea t = service.getTeaByNum("zhangyu");
        System.out.println(t);
    }
}
