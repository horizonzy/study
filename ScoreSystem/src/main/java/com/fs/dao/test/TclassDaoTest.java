package com.fs.dao.test;

import com.fs.dao.TclassDao;
import com.fs.po.Tclass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml"})
public class TclassDaoTest {
    @Autowired
    private TclassDao dao;

    @Test
    public void getAllTclassTest(){
        List<Tclass> tclassList = dao.selectAllTclass();
        for (Tclass t :
                tclassList) {
            System.out.println(t);
        }
    }

    @Test
    public void selectTclassidByNameTest(){

        System.out.println(dao.selectTclassidByName("三"));
    }
}
