package com.fs.controller;


import com.fs.common.PublicData;
import com.fs.po.Stu;
import com.fs.service.StuServiceSupport;
import com.fs.service.TclassServiceSupport;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
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
    public ModelAndView stuLogin(String num, String pwd) {
        ModelAndView mv = new ModelAndView();
        Stu stu = service.getStuByNum(num);
        if(stu!=null) {
            int tclass_id = service.getTclassIdById(stu.getId());
            try {
                boolean b = service.checkNumAndPwd(num, pwd);
                if (b) {
                    mv.addObject("stuid", stu.getId());
                    mv.addObject("stunum", stu.getNum());
                    mv.addObject("tclass_id", tclass_id);
                    mv.setViewName("stu_feedback.jsp");
                    return mv;
                } else {
                    mv.setViewName("login.jsp");
                    mv.addObject("inf", "账号或密码错误");
                    return mv;
                }
            } catch (Exception e) {
                e.printStackTrace();
                mv.setViewName("login.jsp");
                mv.addObject("inf", "账号或密码错误");
                return mv;
            }
        }else {
            mv.setViewName("login.jsp");
            mv.addObject("inf", "账号或密码错误");
            return mv;
        }
    }

    @RequestMapping("resetPwd.do")
    @ResponseBody
    public String resetPwd(String resetNum) {
        try {
            boolean b = service.resetPwd(resetNum);
            if (b) {
                return PublicData.SUCCESS;
            } else {
                return PublicData.FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }

    @RequestMapping("modifyPwd.do")
    @ResponseBody
    public String modifyPwd(String num, String oldPwd, String newPwd) {

        boolean b = service.checkNumAndPwd(num, oldPwd);
        System.out.println(b);
        if (b) {
            boolean b1 = service.modifyPwd(num, newPwd);
            if (b1) {
                return PublicData.SUCCESS;
            } else {
                return PublicData.FAILURE;
            }
        } else {
            return PublicData.FAILURE;
        }
    }

    @RequestMapping("getAllStu.do")
    @ResponseBody
    public List<Stu> getAllStu(int tclass_id, int currentPage) {
        try {
            List<Stu> stuList = service.getAllStu(tclass_id, currentPage);
            return stuList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("deleteOne.do")
    @ResponseBody
    public String deleteOne(int id) {
        try {
            boolean b = service.deleteOne(id);
            if (b) {
                return PublicData.SUCCESS;
            } else {
                return PublicData.FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }

    @RequestMapping("deleteBatch.do")
    @ResponseBody
    public String deleteBatch(HttpServletRequest request) {
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String mysendstu_id = IOUtils.toString(inputStream);
            System.out.println(mysendstu_id);
            JSONArray jsonarr = new JSONArray(mysendstu_id);
            List<Integer> stu_id = new ArrayList<Integer>();
            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonarr.get(i);
                String s = jsonObject.getString("stu_id");
                System.out.println(s);
                stu_id.add(Integer.parseInt(s));
            }
            boolean b = service.deleteBatch(stu_id);
            if (b) {
                return PublicData.SUCCESS;
            } else {
                return PublicData.FAILURE;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }

    @RequestMapping("updateStu.do")
    @ResponseBody
    public String updateStu(int id, String name, String sex, String num, String pwd, int tclass_id) {
        try {
            boolean b = service.updateStu(id, name, sex, num, pwd, tclass_id);
            if (b) {
                return PublicData.SUCCESS;
            } else {
                return PublicData.FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }

    @RequestMapping("insertStu.do")
    @ResponseBody
    public String insertStu(String name, String sex, String num, String pwd, int tclass_id) {
        try {
            boolean b = service.insertStu(name, sex, num, pwd, tclass_id);
            if (b) {
                return PublicData.SUCCESS;
            } else {
                return PublicData.FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PublicData.FAILURE;
        }
    }

    @RequestMapping("getAllStuCount.do")
    @ResponseBody
    public String getAllStuCount(int tclass_id) {
        try {
            int allStuCount = service.getAllStuCount(tclass_id);
            return allStuCount + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("getAllPage.do")
    @ResponseBody
    public String getAllPage(int tclass_id) {
        try {
            int allPage = service.getAllPage(tclass_id);
            return allPage + "";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
