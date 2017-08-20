package com.fs.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.common.PublicDate;
import com.fs.dao.tyCourceDao;
import com.fs.po.tyCource;


@Service
public class tyCourceService implements tyCourceServiceSupport{
@Autowired
private tyCourceDao courceDao;

	@Override
	public List<tyCource> getAllCource(int currentPage5) {
		int firstIndex5=0;
		if(currentPage5>=1 && currentPage5<=this.getTotalPage5()) {
			firstIndex5=(currentPage5-1)*PublicDate.PAGE_SIZE;
		}else {
			firstIndex5=0;
		}
		List<tyCource> list =courceDao.selectAllCource(firstIndex5, PublicDate.PAGE_SIZE);
		return list;
	}
	@Override
	public int getTotalPage5() {
		int totalSize5= courceDao.countSize5();
		int totalPage5 = 0;
		if(totalSize5%PublicDate.PAGE_SIZE>0) {
			totalPage5=totalSize5/PublicDate.PAGE_SIZE+1;
		}else {
			totalPage5=totalSize5/PublicDate.PAGE_SIZE;
		}
		return totalPage5;
	}


	@Override
	public boolean deleteCourceById(int id) {
		int r =courceDao.deleteCourceById(id);
		return r > 0 ? true : false;
	}

	@Override
	public boolean save5(tyCource cource) {

		int r = courceDao.insertCource(cource);
		return r > 0 ? true : false;
	}

	@Override
	public boolean updateCource(tyCource cource) {
		int num = this.courceDao.updateCource(cource);
		return num > 0 ? true : false;
	}
	@Override
    public boolean deleteBatch5(List<Integer> list) {
        int n = courceDao.deleteBatch5(list);
        return n > 0 ? true : false;
    }

	@Override
	public List<tyCource> selectCource(int id, String name) {
		 List<tyCource>  list =courceDao.selectCource(id, name);
	     return list;

	}

}
