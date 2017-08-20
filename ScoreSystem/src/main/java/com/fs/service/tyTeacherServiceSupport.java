package com.fs.service;

import java.util.List;
import com.fs.po.tyTeacher;

public interface tyTeacherServiceSupport {
	public List<tyTeacher> getAllTeacher(int currentPage3);
	public int getTotalPage3();
	public boolean deleteTeacherById(int id);
	public boolean save3(tyTeacher teacher);
	public boolean updateTeacher(tyTeacher teacher);
	public boolean deleteBatch3(List<Integer> list);
	public List<tyTeacher> selectTeacher(int id, String name);
}
