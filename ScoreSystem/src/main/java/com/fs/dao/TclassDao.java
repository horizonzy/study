package com.fs.dao;

import com.fs.po.Tclass;

import java.util.List;

public interface TclassDao {
    List<Tclass> selectAllTclass();
    int selectTclassidByName(String name);

}
