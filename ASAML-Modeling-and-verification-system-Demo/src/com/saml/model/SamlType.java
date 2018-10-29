package com.saml.model;

import java.util.Date;

public class SamlType {
    private Integer id;

    private Integer projectId;
    
    private Integer demandId;
    
    private String projectName;

    private String typeName;

    private String typeExpression;

    private Integer typeRules;

    private Integer typeClassification;

    private Integer typelevel;

    private Date addTime;

    private String remark;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    public String getprojectName() {
        return projectName;
    }

    public void setprojectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeExpression() {
        return typeExpression;
    }

    public void setTypeExpression(String typeExpression) {
        this.typeExpression = typeExpression == null ? null : typeExpression.trim();
    }

    public Integer getTypeRules() {
        return typeRules;
    }

    public void setTypeRules(Integer typeRules) {
        this.typeRules = typeRules;
    }

    public Integer getTypeClassification() {
        return typeClassification;
    }

    public void setTypeClassification(Integer typeClassification) {
        this.typeClassification = typeClassification;
    }

    public Integer getTypelevel() {
        return typelevel;
    }

    public void setTypelevel(Integer typelevel) {
        this.typelevel = typelevel;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}