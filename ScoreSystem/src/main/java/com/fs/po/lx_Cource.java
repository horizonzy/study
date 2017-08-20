package com.fs.po;

public class lx_Cource {
    private int id;
    private String name;
    private int tclass_id;
    private int teacher_id;
    private String date;
	public lx_Cource() {
		super();
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
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
    
}
