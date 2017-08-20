package com.fs.po;

public class JYH_Mid_Date {
   private String cource_name;
   private String teacher_name;
   private String cource_date;
public JYH_Mid_Date() {
	
}
@Override
public String toString() {
	return "Mid_Date [cource_name=" + cource_name + ", teacher_name=" + teacher_name + ", cource_date=" + cource_date
			+ "]";
}
public String getCource_name() {
	return cource_name;
}
public void setCource_name(String cource_name) {
	this.cource_name = cource_name;
}
public String getTeacher_name() {
	return teacher_name;
}
public void setTeacher_name(String teacher_name) {
	this.teacher_name = teacher_name;
}
public String getCource_date() {
	return cource_date;
}
public void setCource_date(String cource_date) {
	this.cource_date = cource_date;
}
   
}
