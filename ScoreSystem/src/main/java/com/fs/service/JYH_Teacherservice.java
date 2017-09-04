package com.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.dao.JYH_Teacherdao;
import com.fs.po.JYH_Cource;
import com.fs.po.JYH_Mid_Date;
import com.fs.po.JYH_Teacher;
@Service
public class JYH_Teacherservice implements JYH_TeacherServiceSupport {
@Autowired
    private JYH_Teacherdao tdao;

@Override
public List<JYH_Cource> selectdate(String courcename, String teachername) {
	List<JYH_Cource> list = tdao.showDate(courcename, teachername);
	return list;
}

@Override
public List<JYH_Cource> selectcource(int tclass_id) {
	List<JYH_Cource> list = tdao.showCource(tclass_id);
	return list;
}

@Override
public List<JYH_Teacher> selectteacher(String name) {
	 List<JYH_Teacher> list = tdao.showTeacher(name);
		return list;
}

@Override
public boolean updateCource(JYH_Mid_Date mid) {
	int num = tdao.updateCource(mid);
	return num >0 ? true: false;
}
	

}
