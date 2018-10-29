package com.saml.samvs.libraries;

public enum feedbacks {
	feedbacks1("帖子详情",5),
	feedbacks2("帖子详情",6);
	private String name;
	
	private Integer para;
	
	feedbacks(String name, Integer para){
		this.name = name;
		this.para = para;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getPara() {
		return para;
	}


	public void setPara(Integer para) {
		this.para = para;
	}
}
