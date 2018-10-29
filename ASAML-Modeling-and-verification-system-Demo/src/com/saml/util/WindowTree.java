package com.saml.util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Administrator
 *
 */
public class WindowTree {
	
	/**
	 * 窗口树窗口节点集合 
	 */
	ArrayList<WTNode> wtNodes;
	/**
	 * 窗口树事件集合 
	 */
	ArrayList<WTEvent> wtEdges;
	/**
	 * 窗口树关系集合 
	 */
	ArrayList<WTBiRelation> wtBiRelations;
	
	
	/**
	 * 窗口树当前处理关系集合 
	 */
	ArrayList<WTBiRelation> currBiRs;
	/**
	 * 窗口树根节点集合、叶子节点集合 
	 */
	ArrayList<WTNode> wtRoots, wtLeafs;
	
	private int testcaseNum;
	
	ArrayList<String> testcases;
	
	private int recursionCnt=0;
	
	/**
	 * 初始化窗口树，可以重写该方法采用读取并解析WindowTree.xml文件方式创初始化
	 */
	public WindowTree(){
		wtNodes = new ArrayList<WTNode>();
		wtEdges = new ArrayList<WTEvent>();
		wtBiRelations = new ArrayList<WTBiRelation>();
		testcases = new ArrayList<String>();
	}
	
	
	public ArrayList<String> getTestcases() {
		return testcases;
	}

	public void clearWT(){
		if (wtNodes.size() > 0) {
			wtNodes.clear();
			wtEdges.clear();
			wtBiRelations.clear();
			currBiRs.clear();
			wtRoots.clear();
			wtLeafs.clear();
			testcaseNum = 0;
			testcases.clear();
		}
	}


	/**
	 * 窗口树自调节，通过遍历初始所有二元关系调增该树中每个节点的出入度以及根节点和叶子节点序列
	 */
	public void reajustWT(){
		wtRoots = new ArrayList<WTNode> (wtNodes);
		wtLeafs = new ArrayList<WTNode> (wtNodes);
		currBiRs = new ArrayList<WTBiRelation> (wtBiRelations);
		
		//若窗口树不为空
		if (null != this){
			//若窗口树的二元关系序列不为空
			if (null != wtBiRelations) {
				Iterator itR = wtBiRelations.iterator();
				WTBiRelation tempBir = new WTBiRelation();
				//遍历二元关系序列
				while(itR.hasNext()){
					tempBir = (WTBiRelation) itR.next();
					//若窗口树的节点序列不为空
					if (null!=wtNodes){
						Iterator<WTNode> itN = wtNodes.iterator();
						WTNode tempNode = new WTNode();
						//遍历节点序列
						while(itN.hasNext()){
							tempNode = (WTNode) itN.next();

							if (tempBir.getFromNode().equals(tempNode.getWindowId()) ) {
								//出度加1，因此不是叶子节点；加1后从原始叶子节点序列剔除
								tempNode.setOutDegree(tempNode.getOutDegree() + 1);
								//若窗口树的叶子节点序列不为空
								if (null != wtLeafs) {
									wtLeafs.remove(tempNode);
								}
							} else if (tempBir.getToNode().equals(tempNode.getWindowId())) {
								//入度加1，因此不是根节点；加1后从原始根节点序列剔除
								tempNode.setInDegree(tempNode.getInDegree() + 1);
								//若窗口树的根节点序列不为空
								if (null != wtRoots) {
									wtRoots.remove(tempNode);
								}
							}
						}
							 
					}
		        }
			}
		}
		//this.printWindowTree();
	}

	/**
	 * @param 当前二元关系集
	 * 遍历整个窗口树，从每一个根节点出发到叶子节点产生所有的测试用例，即api调用序列。
	 */
	public void GenTCSet(){
		testcaseNum = 0;
		testcases.clear();
		
		ArrayList<WTBiRelation> curBiR = new ArrayList<WTBiRelation> (wtBiRelations);
		//若窗口树的根节点序列不为空
		if (null != this.wtRoots){
			Printer.printIndentString(recursionCnt);
			System.out.println("Roots Count = " + this.wtRoots.size());
			Iterator<WTNode> itRoot = wtRoots.iterator();
			WTNode tempNode = new WTNode();
			
			//遍历节点序列
			while(itRoot.hasNext()){
				int  count = 1;
				tempNode = (WTNode) itRoot.next();
				Printer.printIndentString(recursionCnt);
				System.out.println("当前处理根节点是：Root = " +tempNode);
				Iterator itr = curBiR.iterator();
				while(itr.hasNext()){
					WTBiRelation temp = (WTBiRelation) itr.next();		
					if(temp.getFromNode().equals(tempNode.getWindowId())&& (!(temp.getVisitCount()>tempNode.getOutDegree()))) {
						String testcase = "";
						BasicGen(tempNode,curBiR,testcase);
						this.recursionCnt--;
					}
				}
			}
		}
	}
	
	/**
	 * @param w1 窗口
	 * @param curBiR 当前二元关系集
	 * @return 从给定w1到叶子窗口节点为止的事件序列
	 */
	public void BasicGen(WTNode w1, ArrayList<WTBiRelation> curBiR, String path){
		//recursionCnt打印递归深度用
		this.recursionCnt++;
		Printer.printIndentString(recursionCnt);
		System.out.println("当前处理节点为WTNode.Id=" + w1.getWindowId());
		Printer.printIndentString(recursionCnt);
		System.out.println("当前处理节点API为WTNode.APIs=" + w1.getAPIs());
		int index = w1.getOutDegree();
		Printer.printIndentString(recursionCnt);
		System.out.println("当前处理节点出度为WTNode.outDegree=" + index);
		
		if (index == 0){
			//index == 0 表示当前处理节点已经是叶子节点
			Printer.printIndentString(recursionCnt);
			System.out.println("当前处理节点已经是叶子节点!");
			Printer.printIndentString(recursionCnt);
			System.out.println("<<<<Invoke Path [" + (++testcaseNum) + "] : " + path);
			testcases.add(path.substring(0,path.length()-1));
		}
		else {
			//根据源节点找到以此为源的关系Relation					
			Iterator itr = curBiR.iterator();
			String tempPath = "";
			while(itr.hasNext()){
				WTBiRelation temp = (WTBiRelation) itr.next();
				if (temp.getFromNode().equals(w1.getWindowId())) {
					Printer.printIndentString(recursionCnt);
					System.out.println("当前处理关系的边为Edge.Id=" + temp.getEdgeId());
					Printer.printIndentString(recursionCnt);
					System.out.println("当前处理关系为：" + temp.toString());
					
					//根据关系找到对应的边Edge和这个边的EventName
					Iterator ite = this.wtEdges.iterator();
					WTEvent tempEdge = new WTEvent();
					while(ite.hasNext()) {
						tempEdge = (WTEvent) ite.next();
						if (temp.getRelationId().equals(tempEdge.getEventId())){
							tempPath = path + tempEdge.getEventName()+";";
						}
					}
					
					//根据关系找到目标节点WTNode
					Iterator itw = this.wtNodes.iterator();
					WTNode w2 = new WTNode();
					while(itw.hasNext()) {
						w2 = (WTNode) itw.next();
						if (w2.getWindowId().equals(temp.getToNode())){
							temp.setVisitCount(temp.getVisitCount()+1);
							break;
						}
					}
					BasicGen(w2,curBiR,tempPath);
				}
			}
		}
		this.recursionCnt--;
	}
	
	/**
	 * 打印窗口树
	 */
	public void printWindowTree(){
		Printer.printList(this.wtNodes, "Nodes窗口树节点");
		Printer.printList(this.wtEdges, "Edges窗口树边");
		Printer.printList(this.wtBiRelations, "Relations窗口树二元关系");
		Printer.printList(this.wtRoots, "Roots窗口树根节点");
		Printer.printList(this.wtLeafs, "Leaves窗口树叶子节点");
	}


}
