package com.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.dao.lx_ScoreDao;
import com.fs.po.lx_Score;
import com.fs.po.lx_Stu;
@Service
public class lx_ScoreService implements lx_ScoreServiceSupport {
    @Autowired
	private lx_ScoreDao lx_ScoreDao;
	@Override
	public List<lx_Stu> getAllStuId(String name, String teacher_name, String date) {
		List<lx_Stu> list=lx_ScoreDao.getStuId(name, teacher_name, date);
		return list;
	}
	@Override
	public List<lx_Score> getScoreList(String name, String teacher_name, String date) {
		List<lx_Score> list=lx_ScoreDao.getscore(name, teacher_name, date);
		return list;
	}

}
