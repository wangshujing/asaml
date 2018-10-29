package com.saml.samvs.libraries;

public enum outputs {
	outputs1("帖子详情div.output",5),
	outputs2("帖子详情div.output",6);
	private String name;
	
	private Integer para;
	
	outputs(String name, Integer para){
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
