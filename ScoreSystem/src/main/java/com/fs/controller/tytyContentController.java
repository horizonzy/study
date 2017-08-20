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
import com.fs.po.tyContent;
import com.fs.service.tyContentServiceSupport;





@Controller
@RequestMapping("/Content")
public class tytyContentController {
	@Autowired
	private tyContentServiceSupport contentService;

	@RequestMapping("/showAllContent.do")
	@ResponseBody
	public List<tyContent> showAllContent(int currentPage) {
		try {
			List<tyContent> list = contentService.getAllContent(currentPage);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<tyContent>();
		}
	}
	@RequestMapping("/getTotalPage.do")
	@ResponseBody
	public String getTotalPage() {
		int totalPage = contentService.getTotalPage();
		return totalPage+"";
	}


	@RequestMapping("/deleteContentById.do")
	@ResponseBody
	public String deleteContentById(int contentId) {
		try {
			boolean b = contentService.deleteContentById(contentId);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/saveContent.do")
	@ResponseBody
	public String saveContent(String name,int kind_id) {
		boolean b = false;
		try {
			tyContent content = new tyContent();
			content.setKind_id(kind_id);
			content.setName(name);
			b =contentService.save2(content);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/updateMyContent.do")
	@ResponseBody
	public String updateMyContent(int id, String name,int kind_id) {
		if(name.trim().length()==0) {
			return "nameIsNull";
		}
		try {
			tyContent content = new tyContent();
			content.setId(id);
			content.setName(name);
			content.setKind_id(kind_id);
			boolean b = contentService.updateContent(content);
			return b ? PublicDate.SUCCESS : PublicDate.ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return PublicDate.ERROR;
		}
	}
	@RequestMapping("/deleteBatch2.do ")
    @ResponseBody
    public String deleteBatch(HttpServletRequest request) throws JSONException{
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String mysendcontent_id = IOUtils.toString(inputStream);
            System.out.println(mysendcontent_id);
            JSONArray jsonarr = new JSONArray(mysendcontent_id);
            List<Integer> content_id = new ArrayList<Integer>();
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject jsonObject= (JSONObject) jsonarr.get(i);
                String s = jsonObject.getString("content_id");
                System.out.println(s);
                content_id.add(Integer.parseInt(s));
            }
            boolean b = contentService.deleteBatch2(content_id);
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
	@RequestMapping("/showContent.do ")
    @ResponseBody
    public List<tyContent> showContent(int id, String name) {
		List<tyContent> list = contentService.selectContent(id, name);
		return list;
	}
}
