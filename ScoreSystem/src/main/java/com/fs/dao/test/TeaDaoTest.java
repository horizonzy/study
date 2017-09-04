package com.fs.dao.test;

import com.fs.dao.TeaDao;
import com.fs.dao.tyContentDao;
import com.fs.dao.tyCourceDao;
import com.fs.dao.tyKindDao;
import com.fs.po.Tea;
import com.fs.po.tyContent;
import com.fs.po.tyCource;
import com.fs.po.tyKind;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml"})
public class TeaDaoTest {
    @Autowired
    private TeaDao dao;
    @Autowired
    private tyKindDao dao1;

    @Autowired
    private tyCourceDao tyCourceDao;
    @Autowired
    private tyContentDao contentDao;
    @Test
    public void selectTeaByNumTest(){
        Tea t = dao.selectTeaByNum("wangguangro");
        System.out.println(t);
    }

    @Test
    public void tyCourceDaoTest(){
        List<tyCource> list = tyCourceDao.selectAllCource(0,5);
        for (tyCource ty :
                list) {
            System.out.println(ty);
        }
    }
    @Test
    public void testa(){
        List<tyKind> list =  dao1.selectAllKind(0,5);
        for (tyKind kind:list
                ) {
            System.out.println(kind);
        }
    }
    @Test
    public void addContent(){
        tyContent  content = new tyContent();
        content.setName("12");
        content.setC_name("121");
        content.setKind_id(12);
        int n = contentDao.insertContent(content);
        System.out.println(n);
    }

}
