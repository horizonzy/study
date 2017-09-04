package com.fs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.po.lx_Cource;
import com.fs.po.lx_Teacher;
import com.fs.service.lx_CourceServiceSupport;

@Controller
@RequestMapping(value="/cource")
public class lx_CourceController {
    @Autowired
	private lx_CourceServiceSupport conrceService;
    @RequestMapping(value="/showAllCource.do")
    @ResponseBody
    public List<lx_Cource> showAllCource(int tclass_id){
    	try {
			List<lx_Cource> list=conrceService.getAllCource(tclass_id);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<lx_Cource>();
		}
    }
    @RequestMapping(value="/seleceteacherBycourse.do")
	@ResponseBody
	public List<lx_Teacher> seleceteacherBycourseController(String name){
		List<lx_Teacher> listteacher=conrceService.seleceteacherBycourseService(name);
		return listteacher;
	}
	
	@RequestMapping(value="/selectDateBycourseAndteacher.do")
	@ResponseBody
	public  List<lx_Cource> selectDateBycourseAndteacherController(String coursename, String teachername){
		List<lx_Cource> list=conrceService.selectDateBycourseAndteacherService(coursename, teachername);
		return list;
	}
	@RequestMapping(value="/showStuNum.do")
	@ResponseBody
	public int showStuNum(String name,String teacher_name,String date){
		try {
			int b=conrceService.getStuNumById(name, teacher_name, date);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
