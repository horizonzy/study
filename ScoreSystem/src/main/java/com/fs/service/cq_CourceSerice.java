package com.fs.service;

import com.fs.dao.CourceDao;
import com.fs.po.cq_Cource;
import com.fs.po.cq_Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cq_CourceSerice implements cq_CourceServiceSupport {
	@Autowired
	private CourceDao courcedao;
	@Override
	public List<cq_Cource> getAllCource() {
		List<cq_Cource>list=courcedao.selectCource(1);
		return list;
	}
	@Override
	public List<cq_Teacher> getAllTeacher(String name) {
		List<cq_Teacher> list=courcedao.selectAllTeacher(name);
		 for(int i=0;i<list.size();i++){
			 cq_Teacher teacher = list.get(i);
			 String s = teacher.getName();
			 for(int j=i+1;j<list.size();j++){
				 if(  ((cq_Teacher)list.get(j)).getName().equals(s))
				 list.remove(j);
			 }
	}
		 return list;
	}
	@Override
	public List<cq_Cource> getAllTime(String courcename, String teachername) {
		List<cq_Cource>list=courcedao.selectdate(courcename, teachername);
		return list;
	}
	@Override
	public int getId(String courcename2, String teachername2, String date2) {
		int a=courcedao.selectId(courcename2, teachername2, date2);		
		return a;
	}
	
	@Override
	public int getStu(String teachername1,String date,String courcename1) {
		int a=courcedao.selectstu(teachername1, date,courcename1);
		return a;
	}
	
	
}
