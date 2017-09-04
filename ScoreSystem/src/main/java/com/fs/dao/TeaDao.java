package com.fs.dao;

import com.fs.po.Tea;

import java.util.List;

public interface TeaDao {
    Tea selectTeaByNum(String num);
    int selectIdByName(String name);
    List<Tea> selectTeacherByProcess(int process);
}
