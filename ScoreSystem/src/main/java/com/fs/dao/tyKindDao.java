package com.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.tyKind;

public interface tyKindDao {
	public List<tyKind> selectAllKind(@Param("firstIndex2") int firstIndex2, @Param("pageSize2") int pageSize2);
	public int countSize2();
	public int deleteKindById(int id);
	public int insertKind(tyKind kind);
	public int updateKind(tyKind kind);
	public int deleteBatch(List<Integer> list);
	public List<tyKind> selectKind(@Param("id") int id, @Param("name") String name);
}
