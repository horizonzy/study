package com.fs.service;

import java.util.List;

import com.fs.po.lx_Cource;
import com.fs.po.lx_Teacher;

public interface lx_CourceServiceSupport {
    public List<lx_Cource> getAllCource(int tclass_id);
    public List<lx_Teacher> seleceteacherBycourseService(String name);
	public List<lx_Cource> selectDateBycourseAndteacherService(String coursename, String teachername);
	public int getStuNumById(String name, String teacher_name, String date);
}
