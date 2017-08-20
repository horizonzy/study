package com.fs.po;

public class tyTclass {
	private int id;
	private String name;
	private String major;
	
	public tyTclass() {
		
	}

	@Override
	public String toString() {
		return "Tclass [id=" + id + ", name=" + name + ", major=" + major + "]";
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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
}
