package com.fs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.service.ZGFCommentDescriptionServiceSupport;

@Controller
public class ZGFCommentController {
	@Autowired
	private ZGFCommentDescriptionServiceSupport commentdescriptionservice;
	
	@RequestMapping(value="selectdescription.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectdescriptioncontroller(int id){
		String s=commentdescriptionservice.selectdescriptionService(id);
		return s;
	}
}
