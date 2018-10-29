package com.saml.samvs.libraries;

public enum coreDistributeMatch {
	cdMath1(1,"帖子详情","帖子详情div.output"),
	cdMath2(2,"点击帖子超链接","帖子超链接.callback");
	private Integer type;//1FO,2IC,3IO
	private String para1;
	private String para2;
	
	coreDistributeMatch(Integer type,String para1,String para2){
		this.type = type;
		this.para1 = para1;
		this.para2 = para2;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPara1() {
		return para1;
	}
	public void setPara1(String para1) {
		this.para1 = para1;
	}
	public String getPara2() {
		return para2;
	}
	public void setPara2(String para2) {
		this.para2 = para2;
	}
	
	
}
