package com.saml.model;

import java.util.Date;

public class InvokableNode {
    private Integer id;

    private Integer projectId;

    private Integer verifyresultId;

    private Integer type;

    private String nodeId;

    private String nodeName;

    private Date addTime;

    private Integer state;
    
    private String parentNodeId;//当type==2时，有内容，为其对应type=1的结点的nodeId
    
    private String parentNodeName;//当type==2时，有内容，为其对应type=1的结点的nodeName

    private Integer isSelected;//该方法类型的node是否被当前传入的parentID选为函数，1代表是
    
    private Integer demandId;//isSelected为1时，代表该node对应parentID的demandID
    
    private Integer dnmId;//isSelected为1时，代表该node对应parentID的dnmId

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }
    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId == null ? null : parentNodeId.trim();
    }

    public String getParentNodeName() {
        return parentNodeName;
    }

    public void setParentNodeName(String parentNodeName) {
        this.parentNodeName = parentNodeName == null ? null : parentNodeName.trim();
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
    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }
    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }
    public Integer getDnmId() {
        return dnmId;
    }

    public void setDnmId(Integer dnmId) {
        this.dnmId = dnmId;
    }
}