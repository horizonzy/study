package com.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.lx_Score;
import com.fs.po.lx_Stu;

public interface lx_ScoreDao {
	public List<lx_Stu> getStuId(@Param("cource_name") String name,
                                 @Param("teacher_name") String teacher_name, @Param("date") String date);
	public List<lx_Score> getscore(@Param("cource_name") String name,
                                   @Param("teacher_name") String teacher_name, @Param("date") String date);
}
