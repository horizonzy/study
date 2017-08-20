package com.fs.po;


public class lx_Kind {
	private int id;
	private String name;
	private String nick_name;
	
	public lx_Kind() {
		// TODO Auto-generated constructor stub
	}

	public lx_Kind(int id, String name,String nick_name) {
		super();
		this.id = id;
		this.name = name;
		this.nick_name=nick_name;
		}


	@Override
	public String toString() {
		return "Kind [id=" + id + ", name=" + name + ", nick_name=" + nick_name + "]";
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

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	

	
}
