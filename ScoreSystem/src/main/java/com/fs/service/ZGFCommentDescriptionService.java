package com.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.dao.ZGFCommentDao;
import com.fs.po.ZGFComment;

@Service
public class ZGFCommentDescriptionService implements ZGFCommentDescriptionServiceSupport {
	@Autowired
	private  ZGFCommentDao commentdao;
	@Override
	public String selectdescriptionService(int id) {
		List<ZGFComment> list=commentdao.selectdescription(id);
		String ss=new String();
		for(int i=0;i<list.size();i++) {
			ss += list.get(i).getDescription() + "\r\n";
		}
		return ss;
	}

}
