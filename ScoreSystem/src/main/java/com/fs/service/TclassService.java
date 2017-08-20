package com.fs.service;

import com.fs.dao.TclassDao;
import com.fs.po.Tclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TclassService implements TclassServiceSupport {
    @Autowired
    private TclassDao dao;
    @Override
    public List<Tclass> getAllTclass() {
        List<Tclass> list=dao.selectAllTclass();
        return list;
    }

    @Override
    public int getTclassidByName(String name) {
        int n = dao.selectTclassidByName(name);
        return n;
    }
}
