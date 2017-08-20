package com.fs.dao;

import com.fs.po.Stu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuDao {
    String selectPwdByNum(String num);
    int resetPwd(String num);
    Stu selectStuByNum(String num);
    int modifyPwd(@Param("num") String num, @Param("pwd") String pwd);
    List<Stu> selectAllStu(@Param("tclass_id") Integer tclass_id, @Param("name") String name, @Param("firstIndex") int firstIndex, @Param("pageSize") int pageSize);
    int deleteOne(int id);
    int deleteBatch(List<Integer> list);
    int updateStu(@Param("id") int id, @Param("name") String name, @Param("sex") String sex, @Param("num") String num, @Param("pwd") String pwd, @Param("tclass_id") int tclass_id);
    int insertStu(@Param("name") String name, @Param("sex") String sex, @Param("num") String num, @Param("pwd") String pwd, @Param("tclass_id") int tclass_id);
    int allStuCount(@Param("tclass_id") Integer tclass_id, @Param("name") String name);

}
