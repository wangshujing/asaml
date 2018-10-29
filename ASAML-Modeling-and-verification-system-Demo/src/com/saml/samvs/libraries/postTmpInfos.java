package com.saml.samvs.libraries;

public enum postTmpInfos {
	
	postTmpInfos1(1,5,"殡葬法规介绍NEW",3,"殡葬法规的分类有很多比如......NEW"),
	postTmpInfos2(2,2,"自然科学简介NEW",2,"自然科学是一门很xxx的学科......NEW"),
	postTmpInfos3(3,1,"殡葬历史介绍NEW",1,"自古以来的各种殡葬方式很多......NEW");
	private Integer id;

    private Integer postInfoId;

    private String title;

    private Integer themeID;

    private String text;

    
    postTmpInfos(Integer id,Integer postInfoId,String title,Integer themeID,String text){
    	this.id = id;
    	this.postInfoId = postInfoId;
    	this.title = title;
    	this.themeID = themeID;
    	this.text = text;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostInfoId() {
        return postInfoId;
    }

    public void setPostInfoId(Integer postInfoId) {
        this.postInfoId = postInfoId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}
