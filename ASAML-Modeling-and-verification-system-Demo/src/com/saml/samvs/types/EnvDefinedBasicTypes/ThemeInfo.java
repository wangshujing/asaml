package com.saml.samvs.types.EnvDefinedBasicTypes;

import com.saml.samvs.libraries.themeInfos;

public class ThemeInfo {
	private Integer id;

    private String title;

    private Integer forumID;

    public ThemeInfo(String id,String title,String forumID){
    	this.id = Integer.parseInt(id);
    	this.title = title;
    	this.forumID = Integer.parseInt(forumID);
    }
    public ThemeInfo(themeInfos t){
    	this.id = t.getId();
    	this.title = t.getTitle();
    	this.forumID = t.getForumID();
    }
    
    
    
    
    
	@Override
	public String toString() {
		return id + "," + title + "," + forumID ;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThemeInfo other = (ThemeInfo) obj;
		if (forumID == null) {
			if (other.forumID != null)
				return false;
		} else if (!forumID.equals(other.forumID))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
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

    public Integer getForumID() {
        return forumID;
    }

    public void setForumID(Integer forumID) {
        this.forumID = forumID;
    }

    
}
