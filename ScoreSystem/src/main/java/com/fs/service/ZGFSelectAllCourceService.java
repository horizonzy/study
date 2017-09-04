package com.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.dao.ZGFCourceDao;
import com.fs.po.ZGFCource;
import com.fs.po.ZGFTeacher;

@Service
public class ZGFSelectAllCourceService implements ZGFSelectAllCourceServiceSupport{

	@Autowired
	private ZGFCourceDao courcedao;

	@Override
	public List<ZGFCource> selsectallcourceService(int tclass_id){
		 
		 List<ZGFCource> list=courcedao.selsectallcource(tclass_id);
		 for(int i=0;i<list.size();i++){
			 ZGFCource cource = list.get(i);
			 String s = cource.getName();
			 for(int j=i+1;j<list.size();j++){
				 if(((ZGFCource)list.get(j)).getName().equals(s))
				 list.remove(j);
			 }
		 }
		return list;
	}

	
	@Override
	public List<ZGFTeacher> seleceteacherBycourceService(String name){
		List<ZGFTeacher> list=courcedao.seleceteacherBycource(name);
		 for(int i=0;i<list.size();i++){
			 ZGFTeacher teacher = list.get(i);
			 String s = teacher.getName();
			 for(int j=i+1;j<list.size();j++){
				 if(((ZGFTeacher)list.get(j)).getName().equals(s))
				 list.remove(j);
			 }
		 }
		
		return list;
	}


	@Override
	public List<ZGFCource> selectDateBycourceAndteacherService(String courcename,String teachername){
		List<ZGFCource> list=courcedao.selectDateBycourceAndteacher(courcename, teachername);
		return list;
	}


	@Override
	public int selectCourceIDService(String courcename,String teachername,String date){
		int a=courcedao.selectCourceID(courcename, teachername, date);
		return a;
	}


	@Override
	public int selectTclassidService(int id) {
		int a=courcedao.selectTclassid(id);
		return a;
	}


	@Override
	public int CountStudentService(int id) {
		int  a=courcedao.CountStudent(id);
		return a;
	}
	
	


}
