package com.saml.util;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class WTNode {

	//ArrayList APIs;
//	public void init(String apilist){
//		if (!DispatchConstantValues.NULL.equals(apilist)) {
//			
//		}
//		System.out.println("The API list is empty! Plese check!");
//	}
	/**
	 * 窗口ID
	 */
	String windowId;
	/**
	 * 窗口API序列
	 */
	String APIs;
	/**
	 * 窗口节点出度，入度
	 */
	int outDegree, inDegree;
	
	
	public WTNode() {
		super();
	}

	public WTNode(String windowId) {
		super();
		this.windowId = windowId;
	}

	public String getAPIs() {
		return APIs;
	}

	public void setAPIs(String apis) {
		APIs = apis;
	}

	public String getWindowId() {
		return windowId;
	}

	public void setWindowId(String windowId) {
		this.windowId = windowId;
	}

	public int getOutDegree() {
		return outDegree;
	}

	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WTNode [windowId=" + windowId + ", APIs=" + APIs
				+ ", outDegree=" + outDegree + ", inDegree=" + inDegree + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WTNode other = (WTNode) obj;
		if (windowId == null) {
			if (other.windowId != null)
				return false;
		} else if (!windowId.equals(other.windowId))
			return false;
		return true;
	}



	
}
