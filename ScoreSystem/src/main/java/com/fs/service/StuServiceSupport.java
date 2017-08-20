package com.fs.service;

import com.fs.po.Stu;

import java.util.List;

public interface StuServiceSupport {
    boolean checkNumAndPwd(String num, String pwd);
    boolean resetPwd(String num);
    boolean modifyPwd(String num, String pwd);
    List<Stu> getAllStu(Integer tclass_id, String name, int currentPage);
    boolean deleteOne(int id);
    boolean deleteBatch(List<Integer> list);
    boolean updateStu(int id, String name, String sex, String num, String pwd, int tclass_id);
    boolean insertStu(String name, String sex, String num, String pwd, int tclass_id);
    int getAllStuCount(Integer tclass_id, String name);
    int getAllPage(Integer tclass_id, String name);
    Stu getStuByNum(String num);
}
