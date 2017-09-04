package com.fs.service;

import com.fs.po.cq_Cource;
import com.fs.po.cq_Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface cq_CourceServiceSupport {
	public List<cq_Cource> getAllCource(int tclass_id);
	public List<cq_Teacher> getAllTeacher(String name);
	public  List<cq_Cource> getAllTime(String courcename, String teachername);
	public int getId(@Param("courcename2") String courcename2, @Param("teachername2") String teachername2, @Param("date2") String date2);
	public int getStu(@Param("teachername") String teachername1, @Param("time") String date, @Param("courcename") String courcename1);
}
