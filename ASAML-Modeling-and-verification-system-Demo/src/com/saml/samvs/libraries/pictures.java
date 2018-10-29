package com.saml.samvs.libraries;

public enum pictures {
	pictures1(5,"图片1"),
	pictures2(1,"图片2"),
	pictures3(5,"图片3");
	
	private Integer postID;
	
	private String name;

	pictures(Integer postID,String name){
		this.postID = postID;
		this.name = name;
	}
	
	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
