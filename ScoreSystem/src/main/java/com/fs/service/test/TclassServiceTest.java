package com.fs.service.test;

import com.fs.service.TclassServiceSupport;
import com.fs.po.Tclass;
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

   @Test
    public void getAllTclassTest(){
       List<Tclass> tclassList = service.getAllTclass();
       for (Tclass t :
               tclassList) {
           System.out.println(t);
       }
   }

}
