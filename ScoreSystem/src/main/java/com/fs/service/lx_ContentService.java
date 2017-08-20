package com.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.dao.lx_ContentDao;
import com.fs.po.lx_Kind;
@Service
public class lx_ContentService implements lx_ContentServiceSupport {
    @Autowired
	private lx_ContentDao contentdao;
   
	@Override
	public List<Integer> getKindNum() {
		List<Integer> r=contentdao.selectNumByKind();
		return r;
	}

	@Override
	public List<lx_Kind> kindsService() {
		List<lx_Kind> list=contentdao.selectallKind();
		return list;
	}

	@Override
	public List<String> getContentByKindId(int id) {
		List<String> list=contentdao.selectContentByKindId(id);
		return list;
	}

}
