package com.fs.service;

import com.fs.dao.TeaDao;
import com.fs.po.Tea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaService implements TeaServiceSupport {
    @Autowired
    private TeaDao dao;
    @Override
    public Tea getTeaByNum(String name) {
        Tea t = dao.selectTeaByNum(name);
        return t;
    }

    @Override
    public int getIdByName(String name) {
        int id = dao.selectIdByName(name);
        return id;
    }

    @Override
    public List<Tea> getTeacherByProcess(int process) {
        List<Tea> list = dao.selectTeacherByProcess(process);
        return list;
    }

}
