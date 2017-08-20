package com.fs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DispatcherController {
    @RequestMapping("toFeedback.do")
    public ModelAndView toFeedback(int process){
        ModelAndView mv=new ModelAndView();
        mv.addObject("process",process);
        mv.setViewName("tea_feedback.jsp");
        return mv;
    }
    @RequestMapping("toAllgrade.do")
    public ModelAndView toAllgrade(int process){
        ModelAndView mv=new ModelAndView();
        mv.addObject("process",process);
        mv.setViewName("allgrade.jsp");
        return mv;
    }
    @RequestMapping("toPercent.do")
    public ModelAndView toPercent(int process){
        ModelAndView mv=new ModelAndView();
        mv.addObject("process",process);
        mv.setViewName("percent.jsp");
        return mv;
    }
    @RequestMapping("toHeader_manage.do")
    public ModelAndView toHeader_manage(int process){
        ModelAndView mv=new ModelAndView();
        mv.addObject("process",process);
        mv.setViewName("header_manage.jsp");
        return mv;
    }
    @RequestMapping("toAdmin_manage.do")
    public ModelAndView toAdmin_manage(int process){
        ModelAndView mv=new ModelAndView();
        mv.addObject("process",process);
        mv.setViewName("admin_manage.jsp");
        return mv;
    }
    @RequestMapping("toAdvice.do")
    public ModelAndView toAdvice(int process){
        ModelAndView mv=new ModelAndView();
        mv.addObject("process",process);
        mv.setViewName("advice.jsp");
        return mv;
    }

}
