package com.fs.controller;

import com.fs.po.Tclass;
import com.fs.service.TclassServiceSupport;
import com.fs.service.TeaServiceSupport;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TclassController {
    @Autowired
    private TclassServiceSupport service;
    @Autowired
    private TeaServiceSupport serviceSupport;
    @RequestMapping("getAllTclass.do")
    @ResponseBody
    public List<Tclass> getAllTclass(int currentPage) {
        try {
            List<Tclass> tclassList = service.getAllTclassByPage(currentPage);
            return tclassList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("getAllTclass1.do")
    @ResponseBody
    public List<Tclass> getAllTclass() {
        try {
            List<Tclass> tclassList = service.getAllTclass();
            return tclassList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping("toStuPage.do")
    public ModelAndView toStuPage(HttpServletRequest request) {
        String tclass_id = request.getParameter("tclass_id");
        String process = request.getParameter("process");
        System.out.println(tclass_id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("header_manage.jsp");
        mv.addObject("process",Integer.parseInt(process));
        mv.addObject("tclass_id",Integer.parseInt(tclass_id));
        return mv;
    }

    @RequestMapping("getAllTclassPage.do")
    @ResponseBody
    public String getAllTclassPage(){
        try {
            int allPage = service.getAllPage();
            return allPage+"";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    @RequestMapping("getAllTclassCount.do")
    @ResponseBody
    public String getAllTclassCount(){
        try {
            int allCount = service.getAllTclssCount();
            return allCount+"";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("updateTclass.do")
    @ResponseBody
    public String updateTclass(int id,String name,String major,String teacher_name){
        try {
            int teacher_id = serviceSupport.getIdByName(teacher_name);
            boolean b = service.updateTclass(id,name,major,teacher_id);
            if(b){
                return "success";
            }else {
                return "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @RequestMapping("insertTclass.do")
    @ResponseBody
    public String insertTclass(String name,String major,String teacher_name){
        try {
            int teacher_id = serviceSupport.getIdByName(teacher_name);
            boolean b = service.insertTclass(name,major,teacher_id);
            if(b){
                return "seccess";
            }else {
                return "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @RequestMapping("deleteTclass.do")
    @ResponseBody
    public String deleteTclass(int id){
        try {
            boolean b = service.deleteTclass(id);
            if(b){
                return "success";
            }else {
                return "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
    @RequestMapping("deleteBatch1.do")
    @ResponseBody
    public String deleteBatch(HttpServletRequest request){
        List<Integer> list = new ArrayList<>();
        try {
            InputStream inputStream = request.getInputStream();
            String jsonString = IOUtils.toString(inputStream);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String tclass_id = jsonObject.getString("tclass_id");
                list.add(Integer.parseInt(tclass_id));
            }
            boolean b  = service.deleteBatch(list);
            if(b){
                return "success";
            }else {
                return "failure";
            }
        } catch (IOException e) {
            e.printStackTrace();
             return "failure";
        }
    }
}
