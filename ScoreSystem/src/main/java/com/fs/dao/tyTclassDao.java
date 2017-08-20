package com.fs.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.tyTclass;


public interface tyTclassDao {
	public List<tyTclass> selectAllTclass(@Param("firstIndex4") int firstIndex4, @Param("pageSize4") int pageSize4);
	public int countSize4();
	public int deleteTclassById(int id);
	public int insertTclass(tyTclass tclass);
	public int updateTclass(tyTclass tclass);
	public int deleteBatch4(List<Integer> list);
	public List<tyTclass> selectTclass(@Param("id") int id, @Param("name") String name);
}
