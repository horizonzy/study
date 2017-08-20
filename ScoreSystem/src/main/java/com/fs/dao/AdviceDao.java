package com.fs.dao;

import com.fs.po.cq_Advice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdviceDao {
	public List<cq_Advice> selectAllAdvice(@Param("name") String name);
	public List<cq_Advice> selectAdvice(@Param("courcename") String courcename);
	public int insertAdvice(@Param("summary") String summary, @Param("advice") String advice, @Param("cource_id") String cource_id);
}
