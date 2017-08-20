package com.fs.po;

public class tyContent {
	private int id;
	private String name;
	private int kind_id;
	public tyContent() {
		
	}
	@Override
	public String toString() {
		return "Content [id=" + id + ", name=" + name + ", kind_id=" + kind_id + "]";
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
	public int getKind_id() {
		return kind_id;
	}
	public void setKind_id(int kind_id) {
		this.kind_id = kind_id;
	}
	
}