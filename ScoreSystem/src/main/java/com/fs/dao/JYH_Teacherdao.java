package com.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.JYH_Cource;
import com.fs.po.JYH_Mid_Date;
import com.fs.po.JYH_Teacher;

public interface JYH_Teacherdao {
	
	public List<JYH_Cource> showCource(int tclass_id);
	public List<JYH_Teacher> showTeacher(String name);
	public List<JYH_Cource> showDate(@Param("name1") String courcename, @Param("name2") String teachername);
	public int updateCource(JYH_Mid_Date mid);
}
