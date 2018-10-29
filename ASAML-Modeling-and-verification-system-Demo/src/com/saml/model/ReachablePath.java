package com.saml.model;

import java.util.Date;

public class ReachablePath {
    private Integer id;

    private Integer projectId;

    private Integer verifyresultId;

    private String pathExp;

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

    public Integer getVerifyresultId() {
        return verifyresultId;
    }

    public void setVerifyresultId(Integer verifyresultId) {
        this.verifyresultId = verifyresultId;
    }

    public String getPathExp() {
        return pathExp;
    }

    public void setPathExp(String pathExp) {
        this.pathExp = pathExp == null ? null : pathExp.trim();
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