package com.fs.po;


public class cq_Cource {
	private int id;
	private String name;
	private int tclass_id;
	private cq_Teacher teacher_id;
	private String date;
	public cq_Cource (){

	}
	@Override
	public String toString() {
		return "Cource [id=" + id + ", name=" + name + ", tclass_id=" + tclass_id + ", teacher_id=" + teacher_id
				+ ", date=" + date + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTclass_id() {
		return tclass_id;
	}
	public void setTclass_id(int tclass_id) {
		this.tclass_id = tclass_id;
	}
	public cq_Teacher getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(cq_Teacher teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getdate() {
		return date;
	}
	public void setdate(String date) {
		this.date = date;
	}
	
}