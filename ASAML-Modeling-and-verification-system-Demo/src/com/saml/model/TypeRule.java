package com.saml.model;

import java.util.Date;

public class TypeRule {
    private Integer id;

    private Integer projectId;

    private Integer contentId;

    private String ruleName;

    private String ruleCondition;

    private String ruleConclusion;

    private Integer ruleType;

    private Integer ruleEnv;

    private Date addTime;

    private String remark;

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

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleCondition() {
        return ruleCondition;
    }

    public void setRuleCondition(String ruleCondition) {
        this.ruleCondition = ruleCondition == null ? null : ruleCondition.trim();
    }

    public String getRuleConclusion() {
        return ruleConclusion;
    }

    public void setRuleConclusion(String ruleConclusion) {
        this.ruleConclusion = ruleConclusion == null ? null : ruleConclusion.trim();
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getRuleEnv() {
        return ruleEnv;
    }

    public void setRuleEnv(Integer ruleEnv) {
        this.ruleEnv = ruleEnv;
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