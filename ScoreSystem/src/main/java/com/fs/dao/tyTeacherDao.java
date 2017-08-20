package com.fs.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.tyTeacher;

public interface tyTeacherDao {
	public List<tyTeacher> selectAllTeacher(@Param("firstIndex3") int firstIndex3, @Param("pageSize3") int pageSize3);
	public int countSize3();
	public int deleteTeacherById(int id);
	public int insertTeacher(tyTeacher teacher);
	public int updateTeacher(tyTeacher teacher);
	public int deleteBatch3(List<Integer> list);
	public List<tyTeacher> selectTeacher(@Param("id") int id, @Param("name") String name);
}
