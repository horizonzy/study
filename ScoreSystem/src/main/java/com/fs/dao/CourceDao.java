package com.fs.dao;

import com.fs.po.cq_Cource;
import com.fs.po.cq_Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourceDao {
	public List<cq_Cource>selectCource(int tclass_id);
	public List<cq_Teacher> selectAllTeacher(String t_name);
	public List<cq_Cource> selectdate(@Param("name1") String courcename, @Param("name2") String teachername);
	public int selectId(@Param("courcename2") String courcename2, @Param("teachername2") String teachername2, @Param("date2") String date2);
	public int selectstu(@Param("teachername") String teachername1, @Param("time") String date, @Param("courcename") String courcename1);
	
}
