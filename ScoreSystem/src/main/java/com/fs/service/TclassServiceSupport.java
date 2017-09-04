package com.fs.service;

import com.fs.po.Tclass;

import java.util.List;

public interface TclassServiceSupport {
    List<Tclass> getAllTclassByPage(int currentPage);
    int getTclassidByName(String name);
    int getAllTclssCount();
    int getAllPage();
    List<Tclass> getAllTclass();
    boolean updateTclass(int id,String name,String major,int teacher_id);
    boolean insertTclass(String name,String major,int teacher_id);
    boolean deleteTclass(int id);
    boolean deleteBatch(List<Integer> list);
}
