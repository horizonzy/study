package com.fs.service;

import com.fs.dao.AdviceDao;
import com.fs.po.cq_Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cq_AdviceService implements cq_AdviceServiceSupport {
	@Autowired
	private AdviceDao advicedao;
	@Override
	public List<cq_Advice> getAll(String name) {
		List<cq_Advice>list=advicedao.selectAllAdvice(name);
		return list;
	}
	@Override
	public List<cq_Advice> getAdvice(String courcename) {
		List<cq_Advice>list=advicedao.selectAdvice(courcename);
		return list;
	}
	@Override
	public boolean save(String summary, String advice, String cource_id) {
		int r =advicedao.insertAdvice(summary, advice, cource_id);
		return r > 0 ? true : false;
	}
	
	
}
