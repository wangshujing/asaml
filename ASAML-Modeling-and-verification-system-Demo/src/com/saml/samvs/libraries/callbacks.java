package com.saml.samvs.libraries;

public enum callbacks{
	callbacks1("帖子超链接.callback",5),
	callbacks2("帖子超链接.callback",6);
	private String name;
	
	private Integer para;
	
	callbacks(String name, Integer para){
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
