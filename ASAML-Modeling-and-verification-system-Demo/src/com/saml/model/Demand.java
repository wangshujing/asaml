package com.saml.model;

import java.util.Date;

public class Demand {
    private Integer id;

    private Integer projectId;

    private String demandName;

    private Integer demandLevel;

    private String demandInfo;

    private Integer parentId;

    private Date addTime;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName == null ? null : demandName.trim();
    }

    public Integer getDemandLevel() {
        return demandLevel;
    }

    public void setDemandLevel(Integer demandLevel) {
        this.demandLevel = demandLevel;
    }

    public String getDemandInfo() {
        return demandInfo;
    }

    public void setDemandInfo(String demandInfo) {
        this.demandInfo = demandInfo == null ? null : demandInfo.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}