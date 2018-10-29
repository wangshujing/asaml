package com.saml.samvs.types.EnvDefinedBasicTypes;

import com.saml.samvs.libraries.forumOwners;

public class ForumOwner {
	private Integer id;

    private Integer forumID;
    
    private String forumTitle;

    private Integer ownerID;
    
    private String ownerName;
    
   
	public ForumOwner(String id, String forumID, String forumTitle, String ownerID, String ownerName) {
		// TODO Auto-generated constructor stub
    	this.id = Integer.parseInt(id);
    	this.forumID = Integer.parseInt(forumID);
    	this.forumTitle = forumTitle;
    	this.ownerID = Integer.parseInt(ownerID);
    	this.ownerName = ownerName;
	}
    
    public ForumOwner(forumOwners fo){
    	this.id = fo.getId();
    	this.forumID = fo.getForumID();
    	this.forumTitle = fo.getForumTitle();
    	this.ownerID = fo.getOwnerID();
    	this.ownerName = fo.getOwnerName();
    }

	

	
	@Override
	public String toString() {
		return id + "," + forumID + "," + forumTitle + "," + ownerID
				+ "," + ownerName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForumOwner other = (ForumOwner) obj;
		if (forumID == null) {
			if (other.forumID != null)
				return false;
		} else if (!forumID.equals(other.forumID))
			return false;
		if (forumTitle == null) {
			if (other.forumTitle != null)
				return false;
		} else if (!forumTitle.equals(other.forumTitle))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ownerID == null) {
			if (other.ownerID != null)
				return false;
		} else if (!ownerID.equals(other.ownerID))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		return true;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForumID() {
        return forumID;
    }

    public void setForumID(Integer forumID) {
        this.forumID = forumID;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	
}
