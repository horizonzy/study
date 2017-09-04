package com.fs.service;

import com.fs.po.Tea;

import java.util.List;

public interface TeaServiceSupport {
    Tea getTeaByNum(String name);
    int getIdByName(String name);
    List<Tea> getTeacherByProcess(int process);

}
