package com.fs.service;

import java.util.List;

import com.fs.po.tyTclass;


public interface tyTclassServiceSupport {
	List<tyTclass> getAllTclass(int currentPage4);
	int getTotalPage4();
	boolean deleteTclassById(int id);
	boolean save4(tyTclass tclass);
	boolean updateTclass(tyTclass tclass);
	boolean deleteBatch4(List<Integer> list);
	int getTclassIdByName(String name);

}
