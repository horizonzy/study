package com.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.JYH_Comment;
import com.fs.po.JYH_Content;
import com.fs.po.JYH_Cource;
import com.fs.po.JYH_Kind;
import com.fs.po.JYH_Mid_Date;
import com.fs.po.JYH_Score;
import com.fs.po.JYH_Stu;

public interface JYH_Studao {
     public int insertGrade(@Param("grade") int grade, @Param("content_id") int content_id, @Param("cource_id") int cource_id,@Param("stu_id")int stu_id);
     public List<JYH_Mid_Date> showCourse(int id);
     public List<JYH_Stu> showStuName(String num);
     public int insertAdvice(@Param("description") String description, @Param("cource_id") int cource_id);
     public List<JYH_Kind> showKind();
     public List<JYH_Content> showcontent(int kind_id);
     public int selectCourceid(@Param("name1") String courcename, @Param("name2") String teachername, @Param("date") String date);
     public List<JYH_Content> showAllContent();
     public String selectNumById(int id);
}

