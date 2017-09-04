package com.fs.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.tyCource;

public interface tyCourceDao {
	public List<tyCource> selectAllCource(@Param("firstIndex5") int firstIndex5, @Param("pageSize5") int pageSize5);
	public int countSize5();
	public int deleteCourceById(int id);
	public int insertCource(tyCource cource);
	public int updateCource(tyCource cource);
	public int deleteBatch5(List<Integer> list);

}
