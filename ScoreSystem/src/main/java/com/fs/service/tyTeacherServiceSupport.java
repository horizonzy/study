package com.fs.service;

import java.util.List;
import com.fs.po.tyTeacher;

public interface tyTeacherServiceSupport {
	List<tyTeacher> getAllTeacher(int currentPage3);
	int getTotalPage3();
	boolean deleteTeacherById(int id);
	boolean save3(tyTeacher teacher);
	boolean updateTeacher(tyTeacher teacher);
	boolean deleteBatch3(List<Integer> list);
	int getTeacherIdByName(String name);

}
