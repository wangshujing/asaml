package com.saml.samvs.types.EnvDefinedBasicTypes;

import com.saml.samvs.libraries.users;

public class User {
	 private Integer id;
	 
	 private String trueName;
	    
	 public User(String id,String trueName){
		 this.id = Integer.parseInt(id);
		 this.trueName = trueName;
	 }
	 public User(users user){
		 this.id = user.getId();
		 this.trueName = user.getTrueName();
	 }
	 
	 @Override
	public String toString() {
		return id + "," + trueName ;
	}
	public Integer getId() {
	        return id;
	    }

	 public void setId(Integer id) {
	        this.id = id;
	    }

	 public String getTrueName() {
	        return trueName;
	 }

	 public void setTrueName(String trueName) {
	        this.trueName = trueName == null ? null : trueName.trim();
	 }
}
