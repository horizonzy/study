package com.fs.service;

import com.fs.common.PublicData;
import com.fs.dao.TclassDao;
import com.fs.po.Stu;
import com.fs.po.Tclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TclassService implements TclassServiceSupport {
    @Autowired
    private TclassDao dao;
    @Override
    public List<Tclass> getAllTclassByPage(int currentPage) {
        int allPage=this.getAllPage();
        int firstIndex=0;
        if (currentPage >= 1 && currentPage <= allPage) {
            firstIndex = (currentPage - 1) * PublicData.PAGE_SIZE;
        }else if(currentPage>allPage){
            if(currentPage==1){
                firstIndex=0;
            }else{
                currentPage--;
                firstIndex= (currentPage - 1) * PublicData.PAGE_SIZE;
            }
        }
        List<Tclass> stuList=dao.selectAllTclassByPage(firstIndex,PublicData.PAGE_SIZE);
        return stuList;
    }

    @Override
    public int getTclassidByName(String name) {
        int n = dao.selectTclassidByName(name);
        return n;
    }

    @Override
    public int getAllTclssCount() {
        int n = dao.allTclssCount();
        return n;
    }

    @Override
    public int getAllPage() {
        int count = dao.allTclssCount();
        int allPage=0;
        if(count% PublicData.PAGE_SIZE==0){
            allPage=count/PublicData.PAGE_SIZE;
        }else if(count%PublicData.PAGE_SIZE!=0){
            allPage=count/PublicData.PAGE_SIZE+1;
        }
        return allPage;
    }

    @Override
    public List<Tclass> getAllTclass() {
        List<Tclass> list = dao.selectAllTclass();
        return list;
    }

    @Override
    public boolean updateTclass(int id,String name, String major, int teacher_id) {
        int n = dao.updateTclass(id,name,major,teacher_id);
        return n > 0 ? true : false;
    }

    @Override
    public boolean insertTclass(String name, String major, int teacher_id) {
        int n = dao.insertTclass(name,major,teacher_id);
        return n > 0 ? true : false;
    }

    @Override
    public boolean deleteTclass(int id) {
        int n = dao.deleteTclass(id);
        return n > 0 ? true : false;
    }

    @Override
    public boolean deleteBatch(List<Integer> list) {
        int n = dao.deleteBatch(list);
        return n > 0 ? true : false;
    }
}
