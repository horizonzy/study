package com.fs.po;

public class lx_Tclass {
	private int id;
	private String name;
	private String major;
	
	public lx_Tclass() {
		
	}

	public lx_Tclass(int id, String name, String major) {
		super();
		this.id = id;
		this.name = name;
		this.major = major;
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
