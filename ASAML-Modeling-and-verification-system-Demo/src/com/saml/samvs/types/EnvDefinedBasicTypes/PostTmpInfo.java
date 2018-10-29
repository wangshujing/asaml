package com.saml.samvs.types.EnvDefinedBasicTypes;

import com.saml.samvs.libraries.postTmpInfos;

public class PostTmpInfo {
	 	private Integer id;

	    private Integer postInfoId;

	    private String title;

	    private Integer themeID;

	    private String text;

	    public PostTmpInfo(postTmpInfos p){
	    	this.id = p.getId();
	    	this.postInfoId = p.getPostInfoId();
	    	this.title = p.getTitle();
	    	this.themeID = p.getThemeID();
	    	this.text = p.getText();
	    }
	    
	    public PostTmpInfo(String id,String postInfoId,String title,String themeID,String text){
	    	this.id = Integer.parseInt(id);
	    	this.postInfoId = Integer.parseInt(postInfoId);
	    	this.title = title;
	    	this.themeID = Integer.parseInt(themeID);
	    	this.text = text;
	    }
	    
	    
	    

		@Override
		public String toString() {
			return id + "," + postInfoId + "," + title + "," + themeID
					+ "," + text;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PostTmpInfo other = (PostTmpInfo) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (postInfoId == null) {
				if (other.postInfoId != null)
					return false;
			} else if (!postInfoId.equals(other.postInfoId))
				return false;
			if (text == null) {
				if (other.text != null)
					return false;
			} else if (!text.equals(other.text))
				return false;
			if (themeID == null) {
				if (other.themeID != null)
					return false;
			} else if (!themeID.equals(other.themeID))
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
