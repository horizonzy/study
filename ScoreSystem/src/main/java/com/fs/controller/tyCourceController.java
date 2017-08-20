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
import com.fs.po.tyCource;
import com.fs.service.tyCourceServiceSupport;



@Controller
@RequestMapping("/Cource")
public class tyCourceController {
	@Autowired
	private tyCourceServiceSupport courceService;

	@RequestMapping("/showAllCource.do")
	@ResponseBody
	public List<tyCource> showAllCource(int currentPage5) {
		try {
			List<tyCource> list = courceService.getAllCource(currentPage5);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<tyCource>();
		}
	}
	@RequestMapping("/getTotalPage5.do")
	@ResponseBody
	public String getTotalPage5() {
		int totalPage5 = courceService.getTotalPage5();
		return totalPage5+"";
	}
	@RequestMapping("/deleteCourceById.do")
	@ResponseBody
	public String deleteCourceById(int courceId) {
		try {
			boolean b = courceService.deleteCourceById(courceId);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/saveCource.do")
	@ResponseBody
	public String saveCource(String name,int tclass_id,int teacher_id,String date) {
		boolean b = false;
		try {
			tyCource cource=new tyCource();
			cource.setName(name);
			cource.setTclass_id(tclass_id);
			cource.setTeacher_id(teacher_id);
			cource.setDate(date);
			b =courceService.save5(cource);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/updateMyCource.do")
	@ResponseBody
	public String updateMyCource(int id, String name,int tclass_id,int teacher_id,String date) {
		if(name.trim().length()==0) {
			return "nameIsNull";
		}
		try {
			tyCource cource = new tyCource();
			cource.setId(id);
			cource.setName(name);
			cource.setTclass_id(tclass_id);
			cource.setTeacher_id(teacher_id);
			cource.setDate(date);
			
			boolean b = courceService.updateCource(cource);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/deleteBatch5.do ")
    @ResponseBody
    public String deleteBatch(HttpServletRequest request) throws JSONException{
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String mysendcource_id = IOUtils.toString(inputStream);
            System.out.println(mysendcource_id);
            JSONArray jsonarr = new JSONArray(mysendcource_id);
            List<Integer> cource_id = new ArrayList<Integer>();
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject jsonObject= (JSONObject) jsonarr.get(i);
                String s = jsonObject.getString("cource_id");
                System.out.println(s);
                cource_id.add(Integer.parseInt(s));
            }
            boolean b = courceService.deleteBatch5(cource_id);
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
	@RequestMapping("/showCource.do ")
    @ResponseBody
    public List<tyCource> showTclass(int id, String name) {
		List<tyCource> list = courceService.selectCource(id, name);
		return list;
	}
}
