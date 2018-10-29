package com.saml.samvs.libraries;

public enum themeInfos {
	
	themeInfos1(1,"殡葬历史",1),
	themeInfos2(2,"自然科学",2),
	themeInfos3(3,"殡葬法规",3),
	themeInfos4(4,"殡葬书籍",1),
	themeInfos5(5,"服务产品",2),
	themeInfos6(6,"殡葬标准",3),
	themeInfos7(7,"丧葬习俗",1),
	themeInfos8(8,"服务设施",2),
	themeInfos9(9,"重大举措",3);
	private Integer id;

    private String title;

    private Integer forumID;

    themeInfos(Integer id,String title,Integer forumID){
    	this.id = id;
    	this.title = title;
    	this.forumID = forumID;
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
