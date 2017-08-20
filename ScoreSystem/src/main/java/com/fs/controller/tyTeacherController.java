package com.fs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fs.common.PublicDate;
import com.fs.po.tyTeacher;
import com.fs.service.tyTeacherServiceSupport;






@Controller
@RequestMapping("/Teacher")
public class tyTeacherController {
	@Autowired
	private tyTeacherServiceSupport teacherService;

	@RequestMapping("/showAllTeacher.do")
	@ResponseBody
	public List<tyTeacher> showAllTeacher(int currentPage3) {
		try {
			List<tyTeacher> list = teacherService.getAllTeacher(currentPage3);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<tyTeacher>();
		}
	}
	@RequestMapping("/getTotalPage3.do")
	@ResponseBody
	public String getTotalPage3() {
		int totalPage3 = teacherService.getTotalPage3();
		return totalPage3+"";
	}
	@RequestMapping("/deleteTeacherById.do")
	@ResponseBody
	public String deleteTeacherById(int teacherId) {
		try {
			boolean b = teacherService.deleteTeacherById(teacherId);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/saveTeacher.do")
	@ResponseBody
	public String saveTeacher(String name,int process,String num,String pwd) {
		boolean b = false;
		try {
			tyTeacher teacher=new tyTeacher();
			teacher.setName(name);
			teacher.setProcess(process);
			teacher.setNum(num);
			teacher.setPwd(pwd);
			b =teacherService.save3(teacher);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/updateMyTeacher.do")
	@ResponseBody
	public String updateMyTeacher(int id, String name,int process,String num,String pwd) {
		if(name.trim().length()==0) {
			return "nameIsNull";
		}
		try {
			tyTeacher teacher = new tyTeacher();
			teacher.setId(id);
			teacher.setName(name);
			teacher.setProcess(process);
			teacher.setNum(num);
			teacher.setPwd(pwd);
			
			boolean b = teacherService.updateTeacher(teacher);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/deleteBatch3.do ")
    @ResponseBody
    public String deleteBatch(HttpServletRequest request) throws JSONException{
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String mysendteacher_id = IOUtils.toString(inputStream);
            System.out.println(mysendteacher_id);
            JSONArray jsonarr = new JSONArray(mysendteacher_id);
            List<Integer> teacher_id = new ArrayList<Integer>();
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject jsonObject= (JSONObject) jsonarr.get(i);
                String s = jsonObject.getString("teacher_id");
                System.out.println(s);
                teacher_id.add(Integer.parseInt(s));
            }
            boolean b = teacherService.deleteBatch3(teacher_id);
            if(b){
                return PublicDate.SUCCESS;
            }else {
                return PublicDate.ERROR;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return PublicDate.ERROR;
        }
    }
	@RequestMapping("/showTeacher.do ")
    @ResponseBody
    public List<tyTeacher> showTeacher(int id, String name) {
		List<tyTeacher> list = teacherService.selectTeacher(id, name);
		return list;
	}
}
