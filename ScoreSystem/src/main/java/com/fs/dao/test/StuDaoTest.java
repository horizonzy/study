package com.fs.dao.test;

import com.fs.dao.JYH_Studao;
import com.fs.dao.StuDao;
import com.fs.dao.TclassDao;
import com.fs.dao.ZGFContentDao;
import com.fs.po.Stu;
import com.fs.po.ZGFContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-mybatis.xml"})
public class StuDaoTest {
    @Autowired
    private StuDao dao;
    @Autowired
    private TclassDao tclassDao;
    @Autowired
    private ZGFContentDao zgfContentDao;

    @Autowired
    private JYH_Studao jyhStudao;


    public void selectPwdByNumTest(){
        String pwd = dao.selectPwdByNum("xiaoming");
        System.out.println(pwd);
    }

    public void modifyPwdTest(){
        String num="xiaoming";
        String oldPwd="123456";
        String newPwd="xiaoming123456";
        if(dao.selectPwdByNum(num).equals(oldPwd)){
            int n = dao.modifyPwd(num,newPwd);
            if(n>0) {
                System.out.println("更新成功");
            }else {
                System.out.println("更新失败");
            }
        }else {
            System.out.println("该账号或密码错误");
        }
    }

    public void resetPwdTest(){
        String num="xiaoming";
        int n = dao.resetPwd(num);
        if(n>0){
            System.out.println("重置成功");
        }else {
            System.out.println("重置失败");
        }
    }


     public void selectAllStuTest(){

        List<Stu> stuList = dao.selectAllStuByTclassid(1,0,5);
        for (Stu s :
                stuList) {
            System.out.println(s);
        }
    }


    public void updateStuTest(){
         int n = dao.updateStu(10,"小黄","男","xiaohuang","xiaohuang",1);
        System.out.println(n);

    }


    public void insertStuTest(){
        int n = dao.insertStu("小毅","男","xiaoyi","xiaoyi123456",3);
        System.out.println(n);
    }

    public void allStuCountTest(){
        int num = dao.allStuCount(1);
        System.out.println(num);
    }
    @Test
    public void selectStuByNum(){
        Stu stu = dao.selectStuByNum("xiaoming");
        System.out.println(stu);
    }
    @Test
    public void aaa(){
        List<Integer> contents = zgfContentDao.selectcountKindname();
        for (Integer i :
                contents) {
            System.out.println(i);
        }
    }
}
