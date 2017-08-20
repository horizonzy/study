package com.fs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.po.ZGFContent;
import com.fs.po.ZGFKind;
import com.fs.service.ZGFContentServiceSupport;

@Controller
public class ZGFContentController {
	@Autowired
	private ZGFContentServiceSupport contentservice;
	
	@RequestMapping(value="selectallmycontent.do")
	@ResponseBody
	public List<ZGFContent> selectallmycontentController(){
		List<ZGFContent> list=contentservice.selectallmycontentservice();
		return list;
	}
	
	@RequestMapping(value="selectcountKindname.do")
	@ResponseBody
	public List<Integer> selectcountKindnameController(){
		List<Integer> list=contentservice.selectcountKindnameservice();
		return list;
	}
	
	@RequestMapping(value="selectallKind.do")
	@ResponseBody
	public List<ZGFKind> selectallKindcontroller(){
		 List<ZGFKind> list=contentservice.selectallKindservice();
		return list;
	}
	
	@RequestMapping(value="getGrade.do")
	@ResponseBody
	public int[][] getGradeController(int id) {
		int[][] newArray=contentservice.getGradeService(id);
		return newArray;
	}
	
}
