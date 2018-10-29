package com.saml.samvs.libraries;

public enum inputs {

	inputs1("点击帖子超链接",5),
	inputs2("点击帖子超链接",6);
	private String name;
	
	private Integer para;
	
	inputs(String name, Integer para){
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
