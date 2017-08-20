package com.fs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.po.lx_Score;
import com.fs.po.lx_Stu;
import com.fs.service.lx_ScoreServiceSupport;

@Controller
@RequestMapping(value="/score")
public class lx_ScoreController {
	@Autowired
    private lx_ScoreServiceSupport scoreService;
    @RequestMapping(value="/showStuId.do")
    @ResponseBody
    public List<lx_Stu> showStuId(String name, String teacher_name, String date){
    	try {
			List<lx_Stu> list=scoreService.getAllStuId(name, teacher_name, date);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
    }
    @RequestMapping(value="/showScore.do")
    @ResponseBody
    public List<lx_Score> showScore(String name, String teacher_name, String date){
    	try {
			List<lx_Score> list=scoreService.getScoreList(name, teacher_name, date);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
    }
}
