package com.fs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fs.po.lx_Kind;
import com.fs.service.lx_ContentService;

@Controller
@RequestMapping(value="/content")
public class lx_ContentController {
	@Autowired
    private lx_ContentService kService;
    @RequestMapping(value="/showKind.do")
    @ResponseBody
    public List<lx_Kind> showkind(){
    	try {
			List<lx_Kind> list=kService.kindsService();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
    }
    @RequestMapping(value="/showKindNum.do")
    @ResponseBody
    public List<Integer> showKindNum(){
    	try {
			List<Integer> b = kService.getKindNum();
			return b ;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
    }
    
    @RequestMapping(value="/showContentByKindId.do")
    @ResponseBody
    public List<String> showContentByKindId(int id){
    	try {
			List<String> list=kService.getContentByKindId(id);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
    }
}
