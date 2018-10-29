package com.saml.samvs.libraries;

public enum users {
	users1(1,"王某"),
	users2(5,"张三"),
	users3(6,"李某");
	
	private Integer id;
	 
	private String trueName;
	
	users(Integer id,String trueName){
		 this.id = id;
		 this.trueName = trueName;
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
