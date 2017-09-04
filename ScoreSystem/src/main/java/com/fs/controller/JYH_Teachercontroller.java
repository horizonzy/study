package com.fs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.po.JYH_Cource;
import com.fs.po.JYH_Mid_Date;
import com.fs.po.JYH_Teacher;
import com.fs.service.JYH_Teacherservice;

@Controller
public class JYH_Teachercontroller {
	@Autowired
	private JYH_Teacherservice tservice;

	@RequestMapping(value = "tea/showDate.do")
	@ResponseBody
	public List<JYH_Cource> showdate(String courcename, String teachername) {
		try {
			List<JYH_Cource> list = tservice.selectdate(courcename, teachername);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<JYH_Cource>();
		}
	}

	@RequestMapping(value = "tea/showCource.do")
	@ResponseBody
	public List<JYH_Cource> showCource(int tclass_id) {
		try {
			List<JYH_Cource> list = tservice.selectcource(tclass_id);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<JYH_Cource>();
		}
	}

	@RequestMapping(value = "tea/showTeacher.do")
	@ResponseBody
	public List<JYH_Teacher> showTeacher(String name) {
		try {
			List<JYH_Teacher> list = tservice.selectteacher(name);
			System.out.println(list);
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<JYH_Teacher>();
		}
	}

	@RequestMapping(value = "tea/updateCource.do")
	@ResponseBody
	public String updateCource(String cource_name, String cource_date, String teacher_name,int tclass_id) {

		try {
			JYH_Mid_Date mid = new JYH_Mid_Date();
			mid.setCource_date(cource_date);
			mid.setCource_name(cource_name);
			mid.setTeacher_name(teacher_name);
			mid.setTclass_id(tclass_id);
			boolean b = this.tservice.updateCource(mid);
			return b ? "ok" : "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
