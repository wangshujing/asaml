package com.saml.model;

import java.util.Date;

public class VerifyResult {
    private Integer id;

    private Integer projectId;

    private Integer structureResult;

    private String structureInfo;

    private Integer processResult;

    private String processInfo;

    private String xmlPath;

    private String excelPath;

    private String evaluationExcel;

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

    public Integer getStructureResult() {
        return structureResult;
    }

    public void setStructureResult(Integer structureResult) {
        this.structureResult = structureResult;
    }

    public String getStructureInfo() {
        return structureInfo;
    }

    public void setStructureInfo(String structureInfo) {
        this.structureInfo = structureInfo == null ? null : structureInfo.trim();
    }

    public Integer getProcessResult() {
        return processResult;
    }

    public void setProcessResult(Integer processResult) {
        this.processResult = processResult;
    }

    public String getProcessInfo() {
        return processInfo;
    }

    public void setProcessInfo(String processInfo) {
        this.processInfo = processInfo == null ? null : processInfo.trim();
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath == null ? null : xmlPath.trim();
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath == null ? null : excelPath.trim();
    }

    public String getEvaluationExcel() {
        return evaluationExcel;
    }

    public void setEvaluationExcel(String evaluationExcel) {
        this.evaluationExcel = evaluationExcel == null ? null : evaluationExcel.trim();
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