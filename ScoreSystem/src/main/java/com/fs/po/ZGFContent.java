package com.fs.po;

public class ZGFContent {
	private int id;
	private String name;
	private int kindid;
	
	public ZGFContent() {
		
	}

	public ZGFContent(int id, String name, int kindid) {
		super();
		this.id = id;
		this.name = name;
		this.kindid = kindid;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", name=" + name + ", kindid=" + kindid + "]";
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

	public int getkindid() {
		return kindid;
	}

	public void setkindid(int kindid) {
		this.kindid = kindid;
	}
	
	
}
