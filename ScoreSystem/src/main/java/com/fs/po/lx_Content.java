package com.fs.po;

public class lx_Content {
	private int id;
	private String name;
	private int kindid;
	private String nick_name;
	public lx_Content() {
		
	}

	public lx_Content(int id, String name, int kindid,String nick_name) {
		super();
		this.id = id;
		this.name = name;
		this.kindid = kindid;
		this.nick_name=nick_name;
	}

	

	@Override
	public String toString() {
		return "Content [id=" + id + ", name=" + name + ", kindid=" + kindid + ", nick_name=" + nick_name + "]";
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

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	
}
