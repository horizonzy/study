package com.fs.po;

import java.util.Date;

public class ZGFCource {
	private int id;
	private String name;
	private int tclass_id;
	private int teacher_id;
	private String date;
	
	public ZGFCource() {
		
	}

	public ZGFCource(int id, String name, int tclass_id, int teacher_id, String date) {
		super();
		this.id = id;
		this.name = name;
		this.tclass_id = tclass_id;
		this.teacher_id = teacher_id;
		this.date = date;
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
