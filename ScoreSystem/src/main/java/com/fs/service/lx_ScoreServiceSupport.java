package com.fs.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.lx_Score;
import com.fs.po.lx_Stu;

public interface lx_ScoreServiceSupport {
    public List<lx_Stu> getAllStuId(@Param("cource_name") String name,
                                    @Param("teacher_name") String teacher_name, @Param("date") String date);
    public List<lx_Score> getScoreList(String name, String teacher_name, String date);
}
