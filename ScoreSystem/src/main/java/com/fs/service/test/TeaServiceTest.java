package com.fs.service.test;

import com.fs.service.TeaServiceSupport;
import com.fs.po.Tea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml","classpath:config/spring-service.xml"})
public class TeaServiceTest {
    @Autowired
    private TeaServiceSupport service;


    public void getStuByNumTest(){
        Tea t = service.getTeaByNum("zhangyu");
        System.out.println(t);
    }
    @Test
    public void getIdByName(){
        int id = service.getIdByName("王光荣");
        System.out.println(id);
    }
    @Test
    public void getTeacherByProcess(){
        List<Tea> list = service.getTeacherByProcess(2);
        for (Tea t :
                list) {
            System.out.println(t);
        }
    }
}
