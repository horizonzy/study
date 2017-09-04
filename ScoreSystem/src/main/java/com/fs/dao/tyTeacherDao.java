package com.fs.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.tyTeacher;

public interface tyTeacherDao {
	 List<tyTeacher> selectAllTeacher(@Param("firstIndex3") int firstIndex3, @Param("pageSize3") int pageSize3);
	 int countSize3();
	 int deleteTeacherById(int id);
	 int insertTeacher(tyTeacher teacher);
	 int updateTeacher(tyTeacher teacher);
	 int deleteBatch3(List<Integer> list);
	 int selectTeacherIdByName(String name);

}
