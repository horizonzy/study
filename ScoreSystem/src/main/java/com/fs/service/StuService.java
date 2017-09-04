package com.fs.service;

import com.fs.common.PublicData;
import com.fs.dao.StuDao;
import com.fs.po.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuService implements StuServiceSupport {
    @Autowired
    private StuDao dao;
    @Override
    public boolean checkNumAndPwd(String num,String pwd) {
        String resultPwd = dao.selectPwdByNum(num);
        if(resultPwd.equals(pwd)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean resetPwd(String num) {
        int n = dao.resetPwd(num);
        return n > 0 ? true : false;
    }

    @Override
    public boolean modifyPwd(String num, String pwd) {
        int n = dao.modifyPwd(num,pwd);
        return n > 0 ? true : false;
    }

    @Override
    public List<Stu> getAllStu(Integer tclass_id,int currentPage) {
        int allPage=this.getAllPage(tclass_id);
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

        List<Stu> stuList=dao.selectAllStuByTclassid(tclass_id,firstIndex,PublicData.PAGE_SIZE);
        return stuList;

    }

    @Override
    public boolean deleteOne(int id) {
        int n = dao.deleteOne(id);
        return n > 0 ? true : false;
    }

    @Override
    public boolean deleteBatch(List<Integer> list) {
        int n = dao.deleteBatch(list);
        return n > 0 ? true : false;
    }

    @Override
    public boolean updateStu(int id, String name, String sex, String num, String pwd, int tclass_id) {
        int n = dao.updateStu(id,name,sex,num,pwd,tclass_id);
        return n > 0 ? true : false;
    }

    @Override
    public boolean insertStu(String name, String sex, String num, String pwd, int tclass_id) {
        int n = dao.insertStu(name, sex, num, pwd, tclass_id);
        return n > 0 ? true : false;
    }

    @Override
    public int getAllStuCount(Integer tclass_id) {
        int num = dao.allStuCount(tclass_id);
        return num;
    }

    @Override
    public int getAllPage(int tclass_id) {
        int count = dao.allStuCount(tclass_id);
        int allPage=0;
        if(count% PublicData.PAGE_SIZE==0){
            allPage=count/PublicData.PAGE_SIZE;
        }else if(count%PublicData.PAGE_SIZE!=0){
            allPage=count/PublicData.PAGE_SIZE+1;
        }
        return allPage;
    }

    @Override
    public Stu getStuByNum(String num) {
        Stu stu = dao.selectStuByNum(num);
        return stu;
    }

    @Override
    public int getTclassIdById(int id) {
        int tclass_id = dao.selectTclassIdById(id);
        return tclass_id;
    }


}
