package com.fs.dao;

import com.fs.po.Tclass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TclassDao {
    List<Tclass> selectAllTclassByPage(@Param("firstIndex") int firstIndex, @Param("pageSize") int pageSize);
    int selectTclassidByName(String name);
    int allTclssCount();
    List<Tclass> selectAllTclass();
    int updateTclass(@Param("id") int id,@Param("name") String name,@Param("major") String major,@Param("teacher_id") int teacher_id);
    int insertTclass(@Param("name") String name,@Param("major") String major,@Param("teacher_id") int teacher_id);
    int deleteTclass(int id);
    int deleteBatch(List<Integer> list);
 }
