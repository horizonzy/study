package com.fs.service;

import com.fs.dao.TeaDao;
import com.fs.po.Tea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeaService implements TeaServiceSupport {
    @Autowired
    private TeaDao dao;
    @Override
    public Tea getTeaByNum(String name) {
        Tea t = dao.selectTeaByNum(name);
        return t;
    }

}
