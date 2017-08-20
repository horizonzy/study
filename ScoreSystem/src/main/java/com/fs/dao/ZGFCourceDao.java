package com.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.ZGFCource;
import com.fs.po.ZGFTeacher;

public interface ZGFCourceDao {
	//showAllCource()查询所有课程的名字
	public List<ZGFCource> selsectallcource();
	//showtheteacher()通过课程名字，查询上这个课的老师的名字
	public List<ZGFTeacher> seleceteacherBycource(String name);
	//缩小范围，通过老师和课程的名字，显示相关的日期范围
	public List<ZGFCource> selectDateBycourceAndteacher(@Param("name1") String courcename, @Param("name2") String teachername);
	//通过课程名，老师名字和日期，确定唯一的课程ID，以作他用
	public int selectCourceID(@Param("name3") String courcename, @Param("name4") String teachername, @Param("date") String date);
	//根据课程ID查询班级ID
	public int selectTclassid(int id);
	//查询当前班级学生数量
	public int CountStudent(int id);
}
