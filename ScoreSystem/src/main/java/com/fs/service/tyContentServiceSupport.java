package com.fs.service;

import java.util.List;

import com.fs.po.tyContent;

public interface tyContentServiceSupport {
	public List<tyContent> getAllContent(int currentPage);
	public int getTotalPage();
	public boolean deleteContentById(int id);
	public boolean save2(tyContent content);
	public boolean updateContent(tyContent content);
	public boolean deleteBatch2(List<Integer> list);
	public int  getContentIdByName(String name);

}
