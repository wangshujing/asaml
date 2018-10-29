  package com.saml.util;

/**
 * @author Admini strator
 *
 */
public class WTEvent {
	
	/**
	 * 事件ID
	 */
	String eventId;
	
	/**
	 * 事件名
	 */
	String eventName;
	
	/**
	 * 前置条件
	 */
	String preCondition;
	
	/**
	 * 后置条件
	 */
	String postCondition;
	
	/**
	 * 事件API
	 */
	String APIs;
	
	/**
	 * 参数列表
	 */
	String paraList;

	public String getEventId() {
		return eventId;
	}

	/**
	 * @author 鍖楄埅
	 * @param eventId 浜嬩欢ID
	 * @return  
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public String getPostCondition() {
		return postCondition;
	}

	public void setPostCondition(String postCondition) {
		this.postCondition = postCondition;
	}

	public String getAPIs() {
		return APIs;
	}

	public void setAPIs(String aPIs) {
		APIs = aPIs;
	}

	
	public String getParaList() {
		return paraList;
	}

	public void setParaList(String paraList) {
		this.paraList = paraList;
	}

	@Override
	public String toString() {
		return "WTEvent [eventId=" + eventId + ", eventName=" + eventName
				+ ", preCondition=" + preCondition + ", postCondition="
				+ postCondition + ", APIs=" + APIs + ", paraList=" + paraList
				+ "]";
	}

	
}
