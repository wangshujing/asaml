package com.saml.util;

/**
 * @author Administrator
 *
 */
public class WTBiRelation {
	
	/**
	 * 二元关系ID
	 */
	String edgeId;
	
	/**
	 * 开始窗口节点ID
	 */
	String fromNode;
	
	/**
	 * 结束窗口节点ID
	 */
	String toNode;
	
	/**
	 * 访问次数；用于遍历。
	 */
	int visitCount = 0;


	public String getFromNode() {
		return fromNode;
	}

	public void setFromNode(String fromNode) {
		this.fromNode = fromNode;
	}

	public String getToNode() {
		return toNode;
	}

	public void setToNode(String toNode) {
		this.toNode = toNode;
	}

	public String getRelationId() {
		return edgeId;
	}

	public void setRelationId(String edgeId) {
		this.edgeId = edgeId;
	}

	public int getVisitCount() {
		return visitCount;
	}
	
	public int addVisitCount() {
		return visitCount++;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public String getEdgeId() {
		return edgeId;
	}

	public void setEdgeId(String edgeId) {
		this.edgeId = edgeId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WTBiRelation [edgeId=" + edgeId + ", fromNode=" + fromNode
				+ ", toNode=" + toNode + ", visitCount=" + visitCount + "]";
	}

}
