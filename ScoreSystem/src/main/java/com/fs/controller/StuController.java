package com.fs.controller;



import com.fs.common.PublicData;
import com.fs.po.Stu;
import com.fs.service.StuServiceSupport;
import com.fs.service.TclassServiceSupport;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StuController {
    @Autowired
    private StuServiceSupport service;
    @Autowired
    private TclassServiceSupport tclassService;

    @RequestMapping("stuLogin.do")
    public ModelAndView stuLogin(String num,String pwd){
        ModelAndView mv=new ModelAndView();
        Stu stu = service.getStuByNum(num);

        try {
            boolean b = service.checkNumAndPwd(num,pwd);
            if(b){
                mv.addObject("stuid",stu.getId());
                mv.addObject("stunum",stu.getNum());
                mv.setViewName("stu_feedback.jsp");
                return mv;
            }else {
                mv.setViewName("login.jsp");
                mv.addObject("inf","账号或密码错误");
                return mv;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("login.jsp");
            mv.addObject("inf","账号或密码错误");
            return mv;
        }
    }

    @RequestMapping("resetPwd.do")
    @ResponseBody
    public String resetPwd(String resetNum){
        try {
            boolean b = service.resetPwd(resetNum);
            if(b){
                return PublicData.SUCCESS;
            }else {
                return PublicData.FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }
    @RequestMapping("modifyPwd.do")
    @ResponseBody
    public String modifyPwd(String num,String oldPwd,String newPwd){
        System.out.println("111221");
        boolean b = service.checkNumAndPwd(num,oldPwd);
        System.out.println(b);
        if(b){
            boolean b1 = service.modifyPwd(num,newPwd);
            if(b1){
                return PublicData.SUCCESS;
            }else {
                return PublicData.FAILURE;
            }
        }else {
            return PublicData.FAILURE;
        }
    }
    @RequestMapping("getAllStu.do")
    @ResponseBody
    public List<Stu> getAllStu(String tclass_name,String name,int currentPage){
        int tclass_id = tclassService.getTclassidByName(tclass_name.trim());
        try {
            if(tclass_id==0){
            List<Stu> stuList=service.getAllStu(null, name,currentPage);
                return stuList;
            }else {
                List<Stu> stuList=service.getAllStu(tclass_id,name,currentPage);
                return stuList;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("deleteOne.do")
    @ResponseBody
    public String deleteOne(int id){
        try {
            boolean b = service.deleteOne(id);
            if(b){
                return PublicData.SUCCESS;
            }else {
                return PublicData.FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }
    @RequestMapping("deleteBatch.do")
    @ResponseBody
    public String deleteBatch(HttpServletRequest request){
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String mysendstu_id = IOUtils.toString(inputStream);
            System.out.println(mysendstu_id);
            JSONArray jsonarr = new JSONArray(mysendstu_id);
            List<Integer> stu_id = new ArrayList<Integer>();
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject jsonObject= (JSONObject) jsonarr.get(i);
                String s = jsonObject.getString("stu_id");
                System.out.println(s);
                stu_id.add(Integer.parseInt(s));
            }
            boolean b = service.deleteBatch(stu_id);
            if(b){
                return PublicData.SUCCESS;
            }else {
                return PublicData.FAILURE;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }
    @RequestMapping("updateStu.do")
    @ResponseBody
    public String updateStu(int id,String name,String sex,String num,String pwd,int tclass_id){
        try {
            boolean b = service.updateStu(id,name,sex,num,pwd,tclass_id);
            if(b){
                return PublicData.SUCCESS;
            }else {
                return PublicData.FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }
    @RequestMapping("insertStu.do")
    @ResponseBody
    public String insertStu(String name,String sex,String num,String pwd,int tclass_id){
        try {
            boolean b = service.insertStu(name, sex, num, pwd, tclass_id);
            if(b){
                return PublicData.SUCCESS;
            }else {
                return PublicData.FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }
    @RequestMapping("getAllStuCount.do")
    @ResponseBody
    public String getAllStuCount(String tclass_name,String name){
        int tclass_id = tclassService.getTclassidByName(tclass_name.trim());
        try {
            if(tclass_id==0){ //所有学生
                int allStuCount = service.getAllStuCount(null,name);
                return allStuCount+"";
            }else {  //对应的班级学生
                int allStuCount=service.getAllStuCount(tclass_id,name);
                return allStuCount+"";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("getAllPage.do")
    @ResponseBody
    public String getAllPage(String tclass_name,String name){
        int tclass_id = tclassService.getTclassidByName(tclass_name.trim());
        try {
            if(tclass_id==0){ //所有学生
                int allPage = service.getAllPage(null,name);
                return allPage+"";
            }else {  //对应的班级学生
                int allPage=service.getAllPage(tclass_id,name);
                return allPage+"";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
