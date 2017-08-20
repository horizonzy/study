package com.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.dao.lx_CourceDao;
import com.fs.po.lx_Cource;
import com.fs.po.lx_Teacher;
@Service
public class lx_CourceService implements lx_CourceServiceSupport {
    @Autowired
	private lx_CourceDao lx_CourceDao;
	@Override
	public List<lx_Cource> getAllCource() {
		List<lx_Cource> list=lx_CourceDao.selectAllCource();
		return list;
	}
	@Override
	public List<lx_Teacher> seleceteacherBycourseService(String name) {
		List<lx_Teacher> list=lx_CourceDao.seleceteacherBycource(name);
		 for(int i=0;i<list.size();i++){
			 lx_Teacher lx_Teacher = list.get(i);
			 String s = lx_Teacher.getName();
			 for(int j=i+1;j<list.size();j++){
				 if(((lx_Teacher)list.get(j)).getName().equals(s))
				 list.remove(j);
			 }
		 }
		return list;
	}


	@Override
	public List<lx_Cource> selectDateBycourseAndteacherService(String coursename, String teachername) {
		List<lx_Cource> list=lx_CourceDao.selectDateBycourceAndteacher(coursename, teachername);
		return list;
	}
	@Override
	public int getStuNumById(String name,String teacher_name,String date) {
		int r=lx_CourceDao.getStuNum(name, teacher_name, date);
		return r;
	}

}
