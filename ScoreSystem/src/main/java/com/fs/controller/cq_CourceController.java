package com.fs.controller;

import com.fs.po.cq_Cource;
import com.fs.po.cq_Teacher;
import com.fs.service.cq_CourceServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class cq_CourceController {
	@Autowired
	private cq_CourceServiceSupport courceservicesupport;
	@RequestMapping("/getAllCourceMessage.do")
	@ResponseBody
	public List<cq_Cource> getAllCourceMessage(){
		List<cq_Cource> list=courceservicesupport.getAllCource();
		return list;
}	
	@RequestMapping("/getAllTeacherMessage.do")
	@ResponseBody
	public List<cq_Teacher> getAllTeacherMessage(String name){
			System.out.println(name);
			System.out.println(1);
			List<cq_Teacher> list =courceservicesupport.getAllTeacher(name);
			System.out.println(list.size());
			System.out.println(list);
			return list;

	}
	 
	@RequestMapping("/getAllTimeMessage.do")
	@ResponseBody
	public List<cq_Cource> getAllTimeMessage(String courcename,String teachername){
		List<cq_Cource>list=courceservicesupport.getAllTime(courcename, teachername);
		return list;
	}
	
	@RequestMapping("/getIdMessage.do")
	@ResponseBody
	public String getIdMessage(String courcename2, String teachername2, String date2){
		int a=courceservicesupport.getId(courcename2, teachername2, date2);
		return a+"";
	}
	@RequestMapping("/getStuMessage.do")
	@ResponseBody
	public String getStuMessage(String teachername1,String date,String courcename1){
		System.out.println("courcename1="+courcename1+"teachername1="+teachername1+"date="+date);
		int a=courceservicesupport.getStu( teachername1, date,courcename1);
		System.out.println("num="+a);
		return a+"";
	}
}
