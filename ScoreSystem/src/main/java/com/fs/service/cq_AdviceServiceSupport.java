package com.fs.service;

import com.fs.po.cq_Advice;

import java.util.List;

public interface cq_AdviceServiceSupport {
	public List<cq_Advice> getAll(String name);
	public List<cq_Advice> getAdvice(String courcename);
	public boolean save(String summary, String advice, String cource_id);
}
