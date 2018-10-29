package com.saml.samvs.types.EnvDefinedBasicTypes;

import com.saml.samvs.libraries.postInfos;

public class PostInfo {
	 	private Integer id;

	    private String title;

	    private Integer themeID;
	    
	    private Integer visitNumber;

	    private Integer createrID;
	    
	    private Integer state;

	    private String text;

	    
	    public PostInfo(String id,String title,String themeID,String visitNumber,String createrID,String state,String text){
	    	this.id = Integer.parseInt(id);
	    	this.title = title;
	    	this.themeID = Integer.parseInt(themeID);
	    	this.visitNumber = Integer.parseInt(visitNumber);
	    	this.createrID = Integer.parseInt(createrID);
	    	this.state = Integer.parseInt(state);
	    	this.text = text;
	    }
	    public PostInfo(postInfos p){
	    	this.id = p.getId();
	    	this.title = p.getTitle();
	    	this.themeID = p.getThemeID();
	    	this.visitNumber = p.getVisitNumber();
	    	this.createrID = p.getCreaterID();
	    	this.state = p.getState();
	    	this.text = p.getText();
	    }
	    
	    
	    
	    
	   
		@Override
		public String toString() {
			return id + "," + title + "," + themeID + "," + visitNumber
					+ "," + createrID + "," + state + "," + text;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PostInfo other = (PostInfo) obj;
			if (createrID == null) {
				if (other.createrID != null)
					return false;
			} else if (!createrID.equals(other.createrID))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (state == null) {
				if (other.state != null)
					return false;
			} else if (!state.equals(other.state))
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
			if (visitNumber == null) {
				if (other.visitNumber != null)
					return false;
			} else if (!visitNumber.equals(other.visitNumber))
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
