package com.saml.samvs.libraries;

import java.util.List;

import com.saml.samvs.types.EnvDefinedBasicTypes.ThemeInfo;

public enum forumInfos {
	forumInfos1(1,"殡葬文化",3,4),
	forumInfos2(2,"殡葬科技",3,4),
	forumInfos3(3,"殡葬政策",3,4);
	private Integer id;

    private String title;

    private Integer themeNum;
    
    private Integer postNum;
    
    private List<ThemeInfo> themeList;
    
    forumInfos(Integer id,String title,Integer themeNum,Integer postNum){
    	this.id = id;
    	this.title = title;
    	this.themeNum = themeNum;
    	this.postNum = postNum;
    	
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

    

	public Integer getThemeNum() {
		return themeNum;
	}

	public void setThemeNum(Integer themeNum) {
		this.themeNum = themeNum;
	}

	public Integer getPostNum() {
		return postNum;
	}

	public void setPostNum(Integer postNum) {
		this.postNum = postNum;
	}

	public List<ThemeInfo> getThemeList() {
		return themeList;
	}

	public void setThemeList(List<ThemeInfo> themeList) {
		this.themeList = themeList;
	}
	
}
