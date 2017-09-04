package com.fs.service;

import java.util.List;

import com.fs.po.JYH_Cource;
import com.fs.po.JYH_Mid_Date;
import com.fs.po.JYH_Teacher;

public interface JYH_TeacherServiceSupport {
	public List<JYH_Cource> selectdate(String courcename, String teachername);
	public List<JYH_Cource> selectcource(int tclass_id);
	public List<JYH_Teacher> selectteacher(String name);
	public boolean updateCource(JYH_Mid_Date mid);
}
