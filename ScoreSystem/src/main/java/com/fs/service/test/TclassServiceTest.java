package com.fs.service.test;

import com.fs.po.tyKind;
import com.fs.service.TclassServiceSupport;
import com.fs.po.Tclass;
import com.fs.service.tyKindServiceSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml","classpath:config/spring-service.xml"})
public class TclassServiceTest {
   @Autowired
    private TclassServiceSupport service;
    @Autowired
    private tyKindServiceSupport tyKindServiceSupport;
    public void getAllTclassTest(){
       List<Tclass> tclassList = service.getAllTclassByPage(1);
       for (Tclass t :
               tclassList) {
           System.out.println(t);
       }
   }
    @Test
    public void update(){
        service.updateTclass(1,"一班","javaEE",1);
    }

    @Test
    public void insertTclassTest(){
        boolean b = service.insertTclass("三十五班","语文",6);
        System.out.println(b);
    }
    @Test
    public void getAllKindTest(){
        List<tyKind> list = tyKindServiceSupport.getAllKind(1);
        for (tyKind kind:
             list) {
            System.out.println(kind);
        }
    }

}
