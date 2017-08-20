package com.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.lx_Cource;
import com.fs.po.lx_Teacher;

public interface lx_CourceDao {
    public List<lx_Cource> selectAllCource();
	public List<lx_Teacher> seleceteacherBycource(String name);
	public List<lx_Cource> selectDateBycourceAndteacher(@Param("name1")
                                                                String coursename, @Param("name2") String teachername);
    public int getStuNum(@Param("cource_name") String name, @Param("teacher_name") String teacher_name, @Param("date") String date);
}
