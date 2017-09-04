package com.fs.po;

public class tyKind {
	private int id;
	private String name;
	private String nick_name;

	@Override
	public String toString() {
		return "tyKind{" +
				"id=" + id +
				", name='" + name + '\'' +
				", nick_name='" + nick_name + '\'' +
				'}';
	}

	public tyKind(int id, String name, String nick_name) {
		this.id = id;
		this.name = name;
		this.nick_name = nick_name;
	}

	public tyKind() {
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
