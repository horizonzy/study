package com.fs.dao;

import java.util.List;
import java.util.Map;

import com.fs.po.ZGFContent;
import com.fs.po.ZGFKind;
import com.fs.po.ZGFScore;

public interface ZGFContentDao {
	public List<ZGFContent> selectallmycontent();
	public List<Integer> selectcountKindname();
	public List<ZGFKind> selectallKind();
	
	public List<ZGFScore> getGrade(int id);
}
