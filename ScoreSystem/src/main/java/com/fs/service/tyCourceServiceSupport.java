package com.fs.service;

import java.util.List;

import com.fs.po.tyCource;



public interface tyCourceServiceSupport {
	public List<tyCource> getAllCource(int currentPage5);
	public int getTotalPage5();
	public boolean deleteCourceById(int id);
	public boolean save5(tyCource cource);
	public boolean updateCource(tyCource cource);
	public boolean deleteBatch5(List<Integer> list);
	public List<tyCource> selectCource(int id, String name);
}
