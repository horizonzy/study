package com.fs.service;

import java.util.List;

import com.fs.po.tyTclass;


public interface tyTclassServiceSupport {
	public List<tyTclass> getAllTclass(int currentPage4);
	public int getTotalPage4();
	public boolean deleteTclassById(int id);
	public boolean save4(tyTclass tclass);
	public boolean updateTclass(tyTclass tclass);
	public boolean deleteBatch4(List<Integer> list);
	public List<tyTclass> selectTclass(int id, String name);
}
