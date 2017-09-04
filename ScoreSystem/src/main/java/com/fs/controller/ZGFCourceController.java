package com.fs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.po.ZGFCource;
import com.fs.po.ZGFTeacher;
import com.fs.service.ZGFSelectAllCourceServiceSupport;



@Controller
public class ZGFCourceController {
	@Autowired
	private ZGFSelectAllCourceServiceSupport courcecontroller;
	
	@RequestMapping(value="selectallCource.do")
	@ResponseBody
	public List<ZGFCource> selsectallCourceController(int tclass_id){
		List<ZGFCource> list=	courcecontroller.selsectallcourceService(tclass_id);
		return list;
	}
	
	@RequestMapping(value="seleceteacherByCource.do")
	@ResponseBody
	public List<ZGFTeacher> seleceteacherByCourceController(String name){
		List<ZGFTeacher> listteacher=courcecontroller.seleceteacherBycourceService( name);
		return listteacher;
	}
	
	@RequestMapping(value="selectDateByCourceAndteacher.do")
	@ResponseBody
	public  List<ZGFCource> selectDateByCourceAndteacherController(String courcename, String teachername){
		List<ZGFCource> list=courcecontroller.selectDateBycourceAndteacherService( courcename, teachername);
		
		return list;
	}
	
	@RequestMapping(value="selectCourceID.do")
	@ResponseBody
	public int selectCourceIDController(String courcename, String teachername,String date){
		int a=0;
		try{
		a=courcecontroller.selectCourceIDService( courcename, teachername, date);
		return a;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	
	@RequestMapping(value="selectTclassid.do")
	@ResponseBody
	public int selectTclassidController(int courid){
		int a=courcecontroller.selectTclassidService(courid);
		return a;
	}
	
	@RequestMapping(value="CountStudent.do")
	@ResponseBody
	public int CountStudentController(int id){
		int a=courcecontroller.CountStudentService(id);
		return a;
	}
	
}
