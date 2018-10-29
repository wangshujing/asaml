package com.saml.model;

import java.util.Date;

public class Evaluation {
    private Integer id;

    private Integer projectId;

    private Integer verifyresultId;

    private String label;

    private String inParaType;

    private String outParaType;

    private String inPara;

    private String outPara;

    private String realOutParaType;

    private String realOutPara;

    private Integer result;

    private String classType;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getInParaType() {
        return inParaType;
    }

    public void setInParaType(String inParaType) {
        this.inParaType = inParaType == null ? null : inParaType.trim();
    }

    public String getOutParaType() {
        return outParaType;
    }

    public void setOutParaType(String outParaType) {
        this.outParaType = outParaType == null ? null : outParaType.trim();
    }

    public String getInPara() {
        return inPara;
    }

    public void setInPara(String inPara) {
        this.inPara = inPara == null ? null : inPara.trim();
    }

    public String getOutPara() {
        return outPara;
    }

    public void setOutPara(String outPara) {
        this.outPara = outPara == null ? null : outPara.trim();
    }

    public String getRealOutParaType() {
        return realOutParaType;
    }

    public void setRealOutParaType(String realOutParaType) {
        this.realOutParaType = realOutParaType == null ? null : realOutParaType.trim();
    }

    public String getRealOutPara() {
        return realOutPara;
    }

    public void setRealOutPara(String realOutPara) {
        this.realOutPara = realOutPara == null ? null : realOutPara.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType == null ? null : classType.trim();
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