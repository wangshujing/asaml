package com.saml.samvs.types.EnvDefinedBasicTypes;

public class HttpServletResponse {
	
	private String name;
	public HttpServletResponse(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
