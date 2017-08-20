package com.fs.controller;

import com.fs.common.PublicData;
import com.fs.po.cq_Advice;
import com.fs.service.cq_AdviceServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class cq_AdviceController {
	@Autowired
	private cq_AdviceServiceSupport servicesupport;
	
	
	//@RequestMapping(value="showAllMessage.do ",produces = "text/html;charset=UTF-8")
	@RequestMapping("/showAllMessage.do")
	@ResponseBody
	public List<cq_Advice> showAllMessage(String name){
		List<cq_Advice>list=servicesupport.getAll(name);
		
		return list;
	}
	@RequestMapping("/showAdvice.do")
	@ResponseBody
	public List<cq_Advice> showAdvice(String courcename){
		List<cq_Advice>list=servicesupport.getAdvice(courcename);
		return list;
	}
	@RequestMapping("/insertAdvice.do")
	@ResponseBody
	public String saveAdvice(String summary, String advice, String cource_id){
		
		boolean b=servicesupport.save(summary, advice, cource_id);
		return b? PublicData.SUCCESS:PublicData.FAILURE;
	}
}
