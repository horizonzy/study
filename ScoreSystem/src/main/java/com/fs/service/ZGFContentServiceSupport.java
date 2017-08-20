package com.fs.service;

import java.util.List;
import java.util.Map;

import com.fs.po.ZGFContent;
import com.fs.po.ZGFKind;
import com.fs.po.ZGFScore;

public interface ZGFContentServiceSupport {
	public List<ZGFContent> selectallmycontentservice();
	
	public List<Integer> selectcountKindnameservice();
	
	public List<ZGFKind> selectallKindservice();
	
	public int[][] getGradeService(int id);
}
