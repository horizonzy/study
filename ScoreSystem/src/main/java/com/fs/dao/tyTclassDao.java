package com.fs.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.tyTclass;


public interface tyTclassDao {
	List<tyTclass> selectAllTclass(@Param("firstIndex4") int firstIndex4, @Param("pageSize4") int pageSize4);
	int countSize4();
	int deleteTclassById(int id);
	int insertTclass(tyTclass tclass);
	int updateTclass(tyTclass tclass);
	int deleteBatch4(List<Integer> list);
	int selectTclassIdByName(String name);


}
