package com.fs.service;

import java.util.List;

import com.fs.po.tyKind;

public interface tyKindServiceSupport {
	List<tyKind> getAllKind(int currentPage2);
	int getTotalPage2();
	boolean deleteKindById(int id);
	boolean save(String name,String nick_name);
	boolean updateKind(tyKind kind);
	boolean deleteBatch(List<Integer> list);
	int getKindIdByNickName(String nick_name);
	List<String> selectAllNickName();

}
