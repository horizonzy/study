package com.fs.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.ZGFCource;
import com.fs.po.ZGFTeacher;

public interface ZGFSelectAllCourceServiceSupport {
	public List<ZGFCource> selsectallcourceService();
	
	public List<ZGFTeacher> seleceteacherBycourceService(String name);
	
	public List<ZGFCource> selectDateBycourceAndteacherService(String courcename, String teachername);
	
	public int selectCourceIDService(String courcename, String teachername, String date);
	
	public int selectTclassidService(int id);
	
	public int CountStudentService(int id);
}
