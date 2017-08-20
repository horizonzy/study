package com.fs.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.common.PublicDate;
import com.fs.dao.tyKindDao;
import com.fs.po.tyKind;

@Service
public class tyKindService implements tyKindServiceSupport{
@Autowired
private tyKindDao kindDao;

	@Override
	public List<tyKind> getAllKind(int currentPage2) {
		int firstIndex2=0;
		if(currentPage2>=1 && currentPage2<=this.getTotalPage2()) {
			firstIndex2=(currentPage2-1)*PublicDate.PAGE_SIZE;
		}else {
			firstIndex2=0;
		}
		List<tyKind> list =kindDao.selectAllKind(firstIndex2, PublicDate.PAGE_SIZE);
		return list;
	}
	@Override
	public int getTotalPage2() {
		int totalSize2 = kindDao.countSize2();
		int totalPage2 = 0;
		if(totalSize2%PublicDate.PAGE_SIZE>0) {
			totalPage2=totalSize2/PublicDate.PAGE_SIZE+1;
		}else {
			totalPage2=totalSize2/PublicDate.PAGE_SIZE;
		}
		return totalPage2;
	}

	@Override
	public boolean deleteKindById(int id) {
		int r =kindDao.deleteKindById(id);
		return r > 0 ? true : false;
	}

	@Override
	public boolean save(String name) {
		tyKind kind = new tyKind();
		kind.setName(name);

		int r = kindDao.insertKind(kind);
		return r > 0 ? true : false;
	}

	@Override
	public boolean updateKind(tyKind kind) {
		int num = this.kindDao.updateKind(kind);
		return num > 0 ? true : false;
	}
	@Override
    public boolean deleteBatch(List<Integer> list) {
        int n = kindDao.deleteBatch(list);
        return n > 0 ? true : false;
    }

	@Override
	public List<tyKind> selectKind(int id, String name) {
		 List<tyKind>  list = kindDao.selectKind(id, name);
	     return list;

	}
}
