package com.fs.service;

import java.util.List;

import com.fs.po.tyKind;

public interface tyKindServiceSupport {
	public List<tyKind> getAllKind(int currentPage2);
	public int getTotalPage2();
	public boolean deleteKindById(int id);
	public boolean save(String name);
	public boolean updateKind(tyKind kind);
	public boolean deleteBatch(List<Integer> list);
	public List<tyKind> selectKind(int id, String name);
}
