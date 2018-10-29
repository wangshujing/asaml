package com.saml.samvs.types.EnvDefinedMappingTypes;

import java.util.ArrayList;

public class EvaluationResult {

	String methodName;
	ArrayList<Object> params;
	String rvalue;
	
	public EvaluationResult(String methodName, String rvalue) {
		super();
		this.methodName = methodName;
		this.rvalue = rvalue;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public ArrayList<Object> getParams() {
		return params;
	}
	public void setParams(ArrayList<Object> params) {
		this.params = params;
	}
	public String getRvalue() {
		return rvalue;
	}
	public void setRvalue(String rvalue) {
		this.rvalue = rvalue;
	}
	@Override
	public String toString() {
		return "[methodName=" + methodName + ", rvalue=" + rvalue + "]";
	}
		
}
