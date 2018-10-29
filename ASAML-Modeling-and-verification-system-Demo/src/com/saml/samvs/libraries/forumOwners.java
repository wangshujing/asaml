package com.saml.samvs.libraries;

public enum forumOwners {
	
	forumOwner2(2,3,"文化板块",6,"李某"),
	forumOwner3(3,4,"新闻板块",5,"张三"),
	forumOwner1(1,3,"文化板块",5,"张三");
	
	private Integer id;

    private Integer forumID;
    
    private String forumTitle;

    private Integer ownerID;
    
    private String ownerName;
	forumOwners(int id, int forumID, String forumTitle, int ownerID, String ownerName){
		this.id= id;
		this.forumID = forumID;
    	this.forumTitle = forumTitle;
    	this.ownerID = ownerID;
    	this.ownerName = ownerName;
	};
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
