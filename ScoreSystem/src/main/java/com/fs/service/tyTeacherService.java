package com.fs.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.common.PublicDate;
import com.fs.dao.tyTeacherDao;
import com.fs.po.tyTeacher;

@Service
public class tyTeacherService implements tyTeacherServiceSupport{
@Autowired
private tyTeacherDao teacherDao;

	@Override
	public List<tyTeacher> getAllTeacher(int currentPage3) {
		int firstIndex3=0;
		if(currentPage3>=1 && currentPage3<=this.getTotalPage3()) {
			firstIndex3=(currentPage3-1)*PublicDate.PAGE_SIZE;
		}else {
			firstIndex3=0;
		}
		List<tyTeacher> list =teacherDao.selectAllTeacher(firstIndex3, PublicDate.PAGE_SIZE);
		return list;
	}
	@Override
	public int getTotalPage3() {
		int totalSize3 = teacherDao.countSize3();
		int totalPage3 = 0;
		if(totalSize3%PublicDate.PAGE_SIZE>0) {
			totalPage3=totalSize3/PublicDate.PAGE_SIZE+1;
		}else {
			totalPage3=totalSize3/PublicDate.PAGE_SIZE;
		}
		return totalPage3;
	}

	@Override
	public boolean deleteTeacherById(int id) {
		int r =teacherDao.deleteTeacherById(id);
		return r > 0 ? true : false;
	}

	@Override
	public boolean save3(tyTeacher teacher) {
		int r = teacherDao.insertTeacher(teacher);
		return r > 0 ? true : false;
	}

	@Override
	public boolean updateTeacher(tyTeacher teacher) {
		int num = this.teacherDao.updateTeacher(teacher);
		return num > 0 ? true : false;
	}
	@Override
    public boolean deleteBatch3(List<Integer> list) {
        int n = teacherDao.deleteBatch3(list);
        return n > 0 ? true : false;
    }

	@Override
	public int getTeacherIdByName(String name) {
		int id = teacherDao.selectTeacherIdByName(name);
		return id;
	}


}
