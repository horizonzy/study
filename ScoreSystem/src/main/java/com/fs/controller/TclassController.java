package com.fs.controller;

import com.fs.po.Tclass;
import com.fs.service.TclassServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TclassController {
    @Autowired
    private TclassServiceSupport service;
    @RequestMapping("getAllTclass.do")
    @ResponseBody
    public List<Tclass> getAllTclass(){
        try {
            List<Tclass> tclassList = service.getAllTclass();
            return tclassList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
