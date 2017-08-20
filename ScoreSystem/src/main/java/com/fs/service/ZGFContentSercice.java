package com.fs.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.dao.ZGFContentDao;
import com.fs.po.ZGFContent;
import com.fs.po.ZGFKind;
import com.fs.po.ZGFScore;

@Service
public class ZGFContentSercice implements ZGFContentServiceSupport {
	@Autowired
	private ZGFContentDao contentdao;
	
	//查询统计数量！！
	@Override
	public int[][] getGradeService(int id) {
		List<ZGFContent> listcontent=this.selectallmycontentservice();
		List<ZGFScore> listScore=contentdao.getGrade(id);
		
		int[][] anewArray = new int[listcontent.size()][5];
		int grade=0;
		int content=0;
	for(int i=0;i<listcontent.size();i++){
		
		for(int m=0;m<listScore.size();m++){
			grade=listScore.get(m).getGrade();
			content=listScore.get(m).getContent_id();
			
			if(content==listcontent.get(i).getId()){
				if(grade==5){
					anewArray[i][4]++;				//下标i代表对应课程，下标二代表满意度分数；
				}else if(grade==4){
					anewArray[i][3]++;
				}else if(grade==3){
					anewArray[i][2]++;
				}
				else if(grade==2){
					anewArray[i][1]++;
				}
				else if(grade==1){
					anewArray[i][0]++;
					}									
				}
			}
		
		}
		
		return anewArray;
	}
	
	@Override
	public List<ZGFContent> selectallmycontentservice() {
		List<ZGFContent> list=contentdao.selectallmycontent();
		return list;
	}

	@Override
	public List<Integer> selectcountKindnameservice() {
		List<Integer> list=contentdao.selectcountKindname();
		return list;
	}

	@Override
	public List<ZGFKind> selectallKindservice() {
		 List<ZGFKind> list=contentdao.selectallKind();
		 return list;
	}

	

}
