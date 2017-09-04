package com.fs.controller;

import com.fs.po.Tea;
import com.fs.service.TeaServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TeaController {
    @Autowired
    private TeaServiceSupport service;

    @RequestMapping("teaLogin.do")
    public ModelAndView teaLogin(String num,String pwd){
        ModelAndView mv = new ModelAndView();
        try {
            Tea t = service.getTeaByNum(num);
            String pwd1=t.getPwd();
            if(pwd1.equals(pwd)){
                if(t.getProcess()==1){
                    mv.addObject("process",1);
                }else if(t.getProcess()==2){
                    mv.addObject("process",2);
                }else if(t.getProcess()==3){
                    mv.addObject("process",3);
                }
                mv.setViewName("tclass.jsp");
                return mv;
            }else{
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
    @RequestMapping("getTeacherByProcess.do")
    @ResponseBody
    public List<Tea> getTeacher(){
        try {
            List<Tea> list = service.getTeacherByProcess(2);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}

