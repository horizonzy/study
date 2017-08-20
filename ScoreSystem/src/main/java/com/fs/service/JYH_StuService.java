package com.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.dao.JYH_Studao;
import com.fs.po.JYH_Comment;
import com.fs.po.JYH_Content;
import com.fs.po.JYH_Cource;
import com.fs.po.JYH_Kind;
import com.fs.po.JYH_Mid_Date;
import com.fs.po.JYH_Score;
import com.fs.po.JYH_Stu;


@Service
public class JYH_StuService implements JYH_StuServiceSupport {
	@Autowired
	private JYH_Studao studao;


	@Override
	public boolean addGrade(int grade, int content_id, int cource_id,int stu_id) {
		int num = studao.insertGrade(grade,content_id,cource_id,stu_id);
		return num>0 ? true : false;
	}


	@Override
	public List<JYH_Mid_Date> selectCource(int id) {
		List<JYH_Mid_Date> list = studao.showCourse(id);
		return list;
	}


	@Override
	public List<JYH_Stu> showname(String num) {
		List<JYH_Stu> list = studao.showStuName(num);
		return list;
	}


	@Override
	public boolean addAdvice(String description, int cource_id) {
	
		int num = studao.insertAdvice(description,cource_id);
		return num>0 ? true : false;
	}


	@Override
	public List<JYH_Kind> showkind() {
		List<JYH_Kind> list = studao.showKind() ;
		return list;
	}


	@Override
	public List<JYH_Content> showcontent(int kind_id) {
		List<JYH_Content> list = studao.showcontent(kind_id);
		return list;
	}


	@Override
	public int selectcource_id(String courcename, String teachername, String date) {
		int num=studao.selectCourceid(courcename, teachername, date);
		return num;
	}


	@Override
	public List<JYH_Content> showallcontent() {
		List<JYH_Content> list = studao.showAllContent();
		return list;
	}

	@Override
	public String getNumById(int id) {
		String num = studao.selectNumById(id);
		return num;
	}


}












