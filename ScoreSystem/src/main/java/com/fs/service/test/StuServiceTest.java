package com.fs.service.test;

import com.fs.po.tyContent;
import com.fs.service.StuServiceSupport;
import com.fs.service.TclassServiceSupport;
import com.fs.po.Stu;
import com.fs.service.tyContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml","classpath:config/spring-service.xml"})
public class StuServiceTest {
    @Autowired
    private StuServiceSupport service;
    @Autowired
    private TclassServiceSupport tclassService;
    @Autowired
    private tyContentService  tyContentService;
    public void getPwdByNumTeste(){
        boolean b = service.checkNumAndPwd("xiaoming","123456");
        System.out.println(b);
    }


    public void resetPwdTest(){
        boolean b = service.resetPwd("xiaoming");
        System.out.println(b);
    }

    public void modifyPwdTest(){
        boolean b = service.modifyPwd("xiaoming","xiaoming123456");
        System.out.println(b);
    }

    @Test
    public void getAllStuTest(){

        List<Stu> stuList = service.getAllStu(1,1);
        for (Stu s :
                stuList) {
            System.out.println(s);
        }
    }


    public void updateStuTest(){
        boolean b = service.updateStu(10,"小黄","男","xiaohuang","123456",3);
        System.out.println(b);
    }

    public void insertStuTest(){
        service.insertStu("赵延","男","zhaoyan","123456",1);
    }
    @Test
    public void allStuTest(){
        int num = service.getAllStuCount(1);
        System.out.println(num);
    }

    public void allPageTest(){
        int allPage=service.getAllPage(1);
        System.out.println(allPage);
    }
    @Test
    public void addContent(){

    }

}
