package com.saml.samvs.libraries;

public enum httpServletRequests {
	httpServletRequests1("点击帖子超链接","获取帖子详情",5,"帖子详情"),
	httpServletRequests2("点击帖子超链接","获取帖子详情",6,"帖子详情");
	
	private String source;
	private String name;
	private Integer para;
	private String result;
	
	httpServletRequests(String source,String name,Integer para,String result){
		this.source = source;
		this.name = name;
		this.para = para;
		this.result = result;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
