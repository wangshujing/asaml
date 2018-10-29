package com.saml.samvs.types.EnvDefinedBasicTypes;

public class HttpServletRequest {
	
	private String source;
	private String name;
	private Integer para;
	
	
	public HttpServletRequest(String name,String para){
		this.name = name;
		this.para = Integer.parseInt(para);
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
	
	
}
