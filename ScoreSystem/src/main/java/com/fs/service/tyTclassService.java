package com.fs.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.common.PublicDate;
import com.fs.dao.tyTclassDao;
import com.fs.po.tyTclass;


@Service
public class tyTclassService implements tyTclassServiceSupport{
@Autowired
private tyTclassDao tclassDao;

	@Override
	public List<tyTclass> getAllTclass(int currentPage4) {
		int firstIndex4=0;
		if(currentPage4>=1 && currentPage4<=this.getTotalPage4()) {
			firstIndex4=(currentPage4-1)*PublicDate.PAGE_SIZE;
		}else {
			firstIndex4=0;
		}
		List<tyTclass> list =tclassDao.selectAllTclass(firstIndex4, PublicDate.PAGE_SIZE);
		return list;
	}
	@Override
	public int getTotalPage4() {
		int totalSize4 = tclassDao.countSize4();
		int totalPage4 = 0;
		if(totalSize4%PublicDate.PAGE_SIZE>0) {
			totalPage4=totalSize4/PublicDate.PAGE_SIZE+1;
		}else {
			totalPage4=totalSize4/PublicDate.PAGE_SIZE;
		}
		return totalPage4;
	}


	@Override
	public boolean deleteTclassById(int id) {
		int r =tclassDao.deleteTclassById(id);
		return r > 0 ? true : false;
	}

	@Override
	public boolean save4(tyTclass tclass) {

		int r = tclassDao.insertTclass(tclass);
		return r > 0 ? true : false;
	}

	@Override
	public boolean updateTclass(tyTclass tclass) {
		int num = this.tclassDao.updateTclass(tclass);
		return num > 0 ? true : false;
	}
	@Override
    public boolean deleteBatch4(List<Integer> list) {
        int n = tclassDao.deleteBatch4(list);
        return n > 0 ? true : false;
    }

	@Override
	public List<tyTclass> selectTclass(int id, String name) {
		 List<tyTclass>  list =tclassDao.selectTclass(id, name);
	     return list;

	}

}
