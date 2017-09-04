package com.fs.po;

public class tyCource {
	private int id;
	private String name;
	private int tclass_id;
	private int teacher_id;
	private String date;
	private String t_name;
	private String tc_name;

	@Override
	public String toString() {
		return "tyCource{" +
				"id=" + id +
				", name='" + name + '\'' +
				", tclass_id=" + tclass_id +
				", teacher_id=" + teacher_id +
				", date='" + date + '\'' +
				", t_name='" + t_name + '\'' +
				", tc_name='" + tc_name + '\'' +
				'}';
	}

	public tyCource() {
		
	}

	public tyCource(int id, String name, int tclass_id, int teacher_id, String date, String t_name, String tc_name) {
		this.id = id;
		this.name = name;
		this.tclass_id = tclass_id;
		this.teacher_id = teacher_id;
		this.date = date;
		this.t_name = t_name;
		this.tc_name = tc_name;
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

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getTc_name() {
		return tc_name;
	}

	public void setTc_name(String tc_name) {
		this.tc_name = tc_name;
	}
}
