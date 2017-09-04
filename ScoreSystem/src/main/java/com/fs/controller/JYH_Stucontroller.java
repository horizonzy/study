package com.fs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.po.JYH_Content;
import com.fs.po.JYH_Cource;
import com.fs.po.JYH_Kind;
import com.fs.po.JYH_Mid_Date;
import com.fs.po.JYH_Score;
import com.fs.po.JYH_Stu;
import com.fs.service.JYH_StuService;
import org.json.JSONArray;

@Controller
public class JYH_Stucontroller {
	@Autowired
	 private JYH_StuService stuservice;
    @RequestMapping(value="/addGrade.do")
    @ResponseBody
    public String saveGrade(HttpServletRequest request) {
		InputStream inputStream=null;
		Map<String,List> map=new HashMap<>();
		List<Integer> contentList=new ArrayList<>();
		List<Integer> gradeList=new ArrayList<>();
		List<Integer> courceList=new ArrayList<>();
		List<Integer> stuList=new ArrayList<>();
		try {
			inputStream=request.getInputStream();
			String jsonString=IOUtils.toString(inputStream);
			System.out.println(jsonString);
			JSONArray jsonArray=new JSONArray(jsonString);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject= (JSONObject) jsonArray.get(i);
				int content_id = jsonObject.getInt("content_id");
				int grade = jsonObject.getInt("grade");
				int cource_id = jsonObject.getInt("cource_id");
				int stu_id = jsonObject.getInt("stu_id");
				stuList.add(stu_id);
				courceList.add(cource_id);
				gradeList.add(grade);
				contentList.add(content_id);
			}
			map.put("grade",gradeList);
			map.put("content",contentList);
			map.put("cource",courceList);
			map.put("stu",stuList);
			boolean b = stuservice.addGrade(map);
			if (b){
				return "ok";
			}else {
				return "no";
			}

		} catch (IOException e) {
			e.printStackTrace();
			return "no";
		}
    }
    @RequestMapping(value="/showCource.do")
    @ResponseBody
    public List<JYH_Mid_Date> showcource (int tclass_id) {
    	try {
    		List<JYH_Mid_Date> list = stuservice.selectCource(tclass_id);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<JYH_Mid_Date>();
		}
    }
    @RequestMapping(value="/showName.do")
    @ResponseBody
    public List<JYH_Stu> showName (String num) {
    	try {
			List<JYH_Stu> list = stuservice.showname(num);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<JYH_Stu>();
		}
    }
    @RequestMapping(value="/addAdvice.do")
    @ResponseBody
    public String addadvice (String description, int cource_id) {
    	boolean b = false;
    	try {
			b = stuservice.addAdvice(description, cource_id);
			return b ? "ok" : "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
    }
    @RequestMapping(value="/showkind.do")
    @ResponseBody
    public List<JYH_Kind> showkind () {
       try {
    	   
		List<JYH_Kind> list = stuservice.showkind();
		
		return list ;
	} catch (Exception e) {
		
		e.printStackTrace();
		return new ArrayList<JYH_Kind>();
    }
}
    @RequestMapping(value="/showContent.do")
    @ResponseBody
    public List<JYH_Content> showcontent (int kind_id) {
    	try {
			List<JYH_Content> list = stuservice.showcontent(kind_id);
			System.out.println("aaa");
			System.out.println(list);
			return list ;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bbb");
			return new ArrayList<JYH_Content>();
		}
    } 

    @RequestMapping(value="/getcource_id.do")
    @ResponseBody
    public String selectCourceid(String courcename, String teachername,String date) {
		int b=0;
		try{
		 b=stuservice.selectcource_id(courcename.trim(), teachername.trim(), date.trim());
		return b+"" ;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return b+"";
	}
    @RequestMapping(value="/showAllContent.do")
    @ResponseBody
    public List<JYH_Content> showAllContent () {
    	try {
    		List<JYH_Content> list = stuservice.showallcontent();
    		System.out.println(list.size());
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	@RequestMapping(value="/getNumById.do")
	@ResponseBody
	public String getNumById(int id){
		try {
			String num = stuservice.getNumById(id);
			System.out.println("num="+num);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
