package com.saml.samvs.libraries;

public enum postInfos {
	postInfos1(1,"殡葬历史介绍",1,5,5,1,"自古以来的各种殡葬方式很多......"),
	postInfos2(2,"自然科学简介",2,1,1,1,"自然科学是一门很xxx的学科......"),
	postInfos3(5,"殡葬法规介绍",3,7,5,1,"殡葬法规的分类有很多比如......");
	
	
	
	private Integer id;

    private String title;

    private Integer themeID;
    
    private Integer visitNumber;

    private Integer createrID;
    
    private Integer state;

    private String text;
    
    postInfos(Integer id,String title,Integer themeID,Integer visitNumber,Integer createrID,Integer state,String text){
    	this.id = id;
    	this.title = title;
    	this.themeID = themeID;
    	this.visitNumber = visitNumber;
    	this.createrID = createrID;
    	this.state = state;
    	this.text = text;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getThemeID() {
        return themeID;
    }

    public void setThemeID(Integer themeID) {
        this.themeID = themeID;
    }

    

    
    public Integer getVisitNumber() {
		return visitNumber;
	}


	public void setVisitNumber(Integer visitNumber) {
		this.visitNumber = visitNumber;
	}


	public Integer getCreaterID() {
        return createrID;
    }

    public void setCreaterID(Integer createrID) {
        this.createrID = createrID;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}
