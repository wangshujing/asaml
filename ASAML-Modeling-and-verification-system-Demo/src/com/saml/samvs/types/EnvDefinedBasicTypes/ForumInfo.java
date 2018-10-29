package com.saml.samvs.types.EnvDefinedBasicTypes;

import java.util.List;

import com.saml.samvs.libraries.forumInfos;

public class ForumInfo {
	private Integer id;

    private String title;

    private Integer themeNum;
    
    private Integer postNum;
    
    private List<ThemeInfo> themeList;

    public ForumInfo(Integer id,String title,Integer themeNum,Integer postNum,List<ThemeInfo> themeList){
    	this.id = id;
    	this.title = title;
    	this.themeNum = themeNum;
    	this.postNum = postNum;
    	this.themeList = themeList;
    }
    public ForumInfo(forumInfos f){
    	this.id = f.getId();
    	this.title = f.getTitle();
    	this.themeNum = f.getThemeNum();
    	this.postNum = f.getPostNum();
    	
    }
    
    
    @Override
	public String toString() {
		return  id + "," + title + "," + themeNum + "," + postNum
				+ "," + themeList;
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
