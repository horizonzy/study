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
        List<Tclass> tclassList = dao.selectAllTclassByPage(1,5);
        for (Tclass t :
                tclassList) {
            System.out.println(t);
        }
    }

    @Test
    public void selectTclassidByNameTest(){

        System.out.println(dao.selectTclassidByName("三"));
    }


    public void allTclassCount(){
        int n = dao.allTclssCount();
        System.out.println(n);

    }
    @Test
    public void insertTclass(){
        int n = dao.insertTclass("十班","吃饭",1);
        System.out.println(n);
    }
    @Test
    public void deleteTclassTest(){
        int n = dao.deleteTclass(7);
        System.out.println(n);
    }
}
