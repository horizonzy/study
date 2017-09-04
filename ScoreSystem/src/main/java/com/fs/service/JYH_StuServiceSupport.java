package com.fs.service;

import java.util.List;
import java.util.Map;

import com.fs.po.JYH_Content;
import com.fs.po.JYH_Cource;
import com.fs.po.JYH_Kind;
import com.fs.po.JYH_Mid_Date;
import com.fs.po.JYH_Score;
import com.fs.po.JYH_Stu;


public interface JYH_StuServiceSupport {
	
	public boolean addGrade(Map<String,List> map);
	public List<JYH_Mid_Date> selectCource(int tclass_id);
	public List<JYH_Stu> showname(String num);
	public boolean addAdvice(String description, int cource_id);
	public List<JYH_Kind> showkind();
	public List<JYH_Content> showcontent(int kind_id);
	public int selectcource_id(String courcename, String teachername, String date);
	public List<JYH_Content> showallcontent();
	public String getNumById(int id);
}
