package com.fs.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.common.PublicDate;
import com.fs.dao.tyContentDao;
import com.fs.po.tyContent;

@Service
public class tyContentService implements tyContentServiceSupport{
@Autowired
private tyContentDao contentDao;

	@Override
	public List<tyContent> getAllContent(int currentPage) {
		int firstIndex=0;
		if(currentPage>=1 && currentPage<=this.getTotalPage()) {
			firstIndex=(currentPage-1)*PublicDate.PAGE_SIZE;
		}else {
			firstIndex=0;
		}
		List<tyContent> list =contentDao.selectAllContent(firstIndex, PublicDate.PAGE_SIZE);
		return list;
	}

	@Override
	public int getTotalPage() {
		int totalSize = contentDao.countSize();
		int totalPage = 0;
		if(totalSize%PublicDate.PAGE_SIZE>0) {
			totalPage=totalSize/PublicDate.PAGE_SIZE+1;
		}else {
			totalPage=totalSize/PublicDate.PAGE_SIZE;
		}
		return totalPage;
	}
	@Override
	public boolean deleteContentById(int id) {
		int r =contentDao.deleteContentById(id);
		return r > 0 ? true : false;
	}

	@Override
	public boolean save2(tyContent content) {
		int r = contentDao.insertContent(content);
		return r > 0 ? true : false;
	}

	@Override
	public boolean updateContent(tyContent content) {
		int num = this.contentDao.updateContent(content);
		return num > 0 ? true : false;
	}
	@Override
    public boolean deleteBatch2(List<Integer> list) {
        int n = contentDao.deleteBatch2(list);
        return n > 0 ? true : false;
    }

	@Override
	public List<tyContent> selectContent(int id, String name) {
		 List<tyContent>  list = contentDao.selectContent(id, name);
	     return list;

	}

}
