package com.saml.samvs.types.EnvDefinedBasicTypes;

public class input {
	private String name;
	
	private Integer para;
	
	public input(String name, String para){
		this.name = name;
		this.para = Integer.parseInt(para);
	}
	
	@Override
	public String toString() {
		return   name + "," + para;
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
