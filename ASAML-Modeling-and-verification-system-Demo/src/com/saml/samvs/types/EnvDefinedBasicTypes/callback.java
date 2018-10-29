package com.saml.samvs.types.EnvDefinedBasicTypes;

import com.saml.samvs.libraries.callbacks;

public class callback {
	private String name;
	
	private Integer para;
	
	public callback(String name, String para){
		this.name = name;
		this.para =Integer.parseInt(para);
	}
	
	
	

	@Override
	public String toString() {
		return   name + "," + para;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		callback other = (callback) obj;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (para == null) {
			if (other.para != null)
				return false;
		} else if (!para.equals(other.para))
			return false;
		return true;
	}


	public callback(callbacks cb) {
		this.name = cb.getName();
		this.para = cb.getPara();
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
