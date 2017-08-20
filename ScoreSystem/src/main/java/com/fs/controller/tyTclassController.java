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
import com.fs.po.tyTclass;
import com.fs.service.tyTclassServiceSupport;


@Controller
@RequestMapping("/Tclass")
public class tyTclassController {
	@Autowired
	private tyTclassServiceSupport tclassService;

	@RequestMapping("/showAllTclass.do")
	@ResponseBody
	public List<tyTclass> showAllTclass(int currentPage4) {
		try {
			List<tyTclass> list = tclassService.getAllTclass(currentPage4);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<tyTclass>();
		}
	}
	@RequestMapping("/getTotalPage4.do")
	@ResponseBody
	public String getTotalPage4() {
		int totalPage4 = tclassService.getTotalPage4();
		return totalPage4+"";
	}
	@RequestMapping("/deleteTclassById.do")
	@ResponseBody
	public String deleteTclassById(int tclassId) {
		try {
			boolean b = tclassService.deleteTclassById(tclassId);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/saveTclass.do")
	@ResponseBody
	public String saveTclass(String name,String major) {
		boolean b = false;
		try {
			tyTclass tclass=new tyTclass();
			tclass.setName(name);
			tclass.setMajor(major);
			b =tclassService.save4(tclass);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/updateMyTclass.do")
	@ResponseBody
	public String updateMyTclass(int id, String name,String major) {
		if(name.trim().length()==0) {
			return "nameIsNull";
		}
		try {
			tyTclass tclass = new tyTclass();
			tclass.setId(id);
			tclass.setName(name);
			tclass.setMajor(major);
			
			boolean b = tclassService.updateTclass(tclass);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/deleteBatch4.do ")
    @ResponseBody
    public String deleteBatch(HttpServletRequest request) throws JSONException{
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String mysendtclass_id = IOUtils.toString(inputStream);
            System.out.println(mysendtclass_id);
            JSONArray jsonarr = new JSONArray(mysendtclass_id);
            List<Integer> tclass_id = new ArrayList<Integer>();
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject jsonObject= (JSONObject) jsonarr.get(i);
                String s = jsonObject.getString("tclass_id");
                System.out.println(s);
                tclass_id.add(Integer.parseInt(s));
            }
            boolean b = tclassService.deleteBatch4(tclass_id);
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
	@RequestMapping("/showTclass.do ")
    @ResponseBody
    public List<tyTclass> showTclass(int id, String name) {
		List<tyTclass> list = tclassService.selectTclass(id, name);
		return list;
	}
}
