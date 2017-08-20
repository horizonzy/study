package com.fs.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fs.po.tyContent;

public interface tyContentDao {
	public List<tyContent> selectAllContent(@Param("firstIndex") int firstIndex, @Param("pageSize") int pageSize);
	public int countSize();
	public int deleteContentById(int id);
	public int insertContent(tyContent contnet);
	public int updateContent(tyContent content);
	public int deleteBatch2(List<Integer> list);
	public List<tyContent> selectContent(@Param("id") int id, @Param("name") String name);
}
