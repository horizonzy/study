package com.fs.dao.test;

import com.fs.dao.TeaDao;
import com.fs.po.Tea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml"})
public class TeaDaoTest {
    @Autowired
    private TeaDao dao;

    @Test
    public void selectTeaByNumTest(){
        Tea t = dao.selectTeaByNum("wangguangro");
        System.out.println(t);
    }
}
