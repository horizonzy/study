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
import com.fs.po.tyKind;
import com.fs.service.tyKindServiceSupport;




@Controller
@RequestMapping("/Kind")
public class tyKindController {
	@Autowired
	private tyKindServiceSupport kindService;

	@RequestMapping("/showAllKind.do")
	@ResponseBody
	public List<tyKind> showAllTclass(int currentPage2) {
		try {
			List<tyKind> list = kindService.getAllKind(currentPage2);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<tyKind>();
		}
	}
	@RequestMapping("/getTotalPage2.do")
	@ResponseBody
	public String getTotalPage2() {
		int totalPage2 = kindService.getTotalPage2();
		return totalPage2+"";
	}

	@RequestMapping("/deleteKindById.do")
	@ResponseBody
	public String deleteKindById(int kindId) {
		try {
			boolean b = kindService.deleteKindById(kindId);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/saveKind.do")
	@ResponseBody
	public String saveKind(String name,String nick_name) {
		boolean b = false;
		try {
			b = kindService.save(name,nick_name);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/updateMyKind.do")
	@ResponseBody
	public String updateMyKind(int id, String name,String nick_name) {
		if(name.trim().length()==0) {
			return "nameIsNull";
		}
		try {
			tyKind kind = new tyKind();
			kind.setId(id);
			kind.setName(name);
			kind.setNick_name(nick_name);
			boolean b = kindService.updateKind(kind);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/deleteBatch.do ")
    @ResponseBody
    public String deleteBatch(HttpServletRequest request) throws JSONException{
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String mysendkind_id = IOUtils.toString(inputStream);
            System.out.println(mysendkind_id);
            JSONArray jsonarr = new JSONArray(mysendkind_id);
            List<Integer> kind_id = new ArrayList<Integer>();
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject jsonObject= (JSONObject) jsonarr.get(i);
                String s = jsonObject.getString("kind_id");
                System.out.println(s);
                kind_id.add(Integer.parseInt(s));
            }
            boolean b = kindService.deleteBatch(kind_id);
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
	@RequestMapping("/allKindNickName.do")
	@ResponseBody
	public List<String> allKindNickName(){
		System.out.println("进入了121211");
		try {
			List<String> list = kindService.selectAllNickName();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
