package com.fs.po;

public class JYH_Comment {
   
    private String description;
    private int cource_id;
	public JYH_Comment() {
		
	}
	@Override
	public String toString() {
		return "Comment [description=" + description + ", cource_id=" + cource_id + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCource_id() {
		return cource_id;
	}
	public void setCource_id(int cource_id) {
		this.cource_id = cource_id;
	}
    
}
