package com.saml.util;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.Iterator; 

import com.saml.util.StateTransitionInfo;

public class XMLParser {
	
	private final static String stXML = "StateTransitionRule.xml";
	//private final static String wtXML = "C:\\TEMP\\DesignWindowTree.xml";
	//private final static String wtXML = "C:\\TEMP\\DesignSample.xml";

	
	public XMLParser(){
		
	}
	
	public void getXMLStateTransitionRules(StateTransitionInfo stateTransitionInfo) throws Exception {
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
        path=path.replace('/', '\\'); // 将/换成\  
        path=path.replace("file:", ""); //去掉file:  
        path=path.replace("classes\\", ""); //去掉class\\  
        path=path.replace("\\WEB-INF", ""); //去掉\\WEB-INF  
        path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...  
        path+="resource\\satGraph\\"+stXML;
        
        System.out.println(path);
        
        stateTransitionInfo.clearST();
		
        SAXReader reader = new SAXReader();  
        Document document = reader.read(new File(path)); 
        
        //获取根节点元素对象 
        Element root = document.getRootElement();  
        
        //states
        Element stateNode = root.element("States");
        Iterator<Element> stateIter = stateNode.elementIterator(); 
        while (stateIter.hasNext()) {  
            // 获取某个子节点对象  
            Element tempElement = stateIter.next();
            stateTransitionInfo.getStates().add(tempElement.attributeValue("name").trim());
        }
        
        //edges
        Element edgeNode = root.element("Edges");
        Iterator<Element> edgeIter = edgeNode.elementIterator(); 
        while (edgeIter.hasNext()) {  
            // 获取某个子节点对象  
            Element tempElement = edgeIter.next();
            stateTransitionInfo.getEdges().add(tempElement.attributeValue("Events").trim());
        }
        
        //Rules
        Element ruleNode = root.element("Transitions");
        Iterator<Element> ruleIter = ruleNode.elementIterator(); 
        while (ruleIter.hasNext()) {  
            // 获取某个子节点对象  
            Element tempElement = ruleIter.next();
            StateTransitionRule tempSTRule = new StateTransitionRule();
            tempSTRule.setFromState(tempElement.attributeValue("fromState").trim());
            tempSTRule.setEvents(tempElement.attributeValue("tid").trim());
            tempSTRule.setToState(tempElement.attributeValue("toState").trim());
            stateTransitionInfo.getStRuleSet().add(tempSTRule);
        }
        
	}
	
	public static void getXMLWindowTree(WindowTree windowTree,String path) throws Exception {
		//String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
        //path=path.replace('/', '\\'); // 将/换成\ 
        /*path=path.replace("file:", ""); //去掉file:  
        path=path.replace("classes\\", ""); //去掉class\\  
        path=path.replace("\\WEB-INF", ""); //去掉\\WEB-INF  
        path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb... 
        path+="resource\\satGraph\\"+wtXML;*/
        
        windowTree.clearWT();
		
        SAXReader reader = new SAXReader();  
        Document document = null;
        System.out.println(path);
        try {
        	document = reader.read(new File(path));  
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
       
        Element root = null ;
        if (document != null) {
        	//获取根节点元素对象
        	root = document.getRootElement();  
        }
        
        //Windows
        Element stateNode = root.element("Windows");
        Iterator<Element> stateIter = stateNode.elementIterator(); 
        while (stateIter.hasNext()) {  
            // 获取某个子节点对象  
            Element tempElement = stateIter.next();
            WTNode wtn = new WTNode();
            wtn.setWindowId(tempElement.attributeValue("id").trim());
            wtn.setAPIs(tempElement.attributeValue("APIs").trim());
            windowTree.wtNodes.add(wtn);
        }
        
        //edges
        Element edgeNode = root.element("Edges");
        Iterator<Element> edgeIter = edgeNode.elementIterator(); 
        while (edgeIter.hasNext()) {  
            // 获取某个子节点对象  
            Element tempElement = edgeIter.next();
            WTEvent wte = new WTEvent();
            wte.setEventId(tempElement.attributeValue("id").trim());
            wte.setEventName(tempElement.attributeValue("Events").trim());
            //wte.setAPIs(tempElement.attributeValue("APIs").trim());
            windowTree.wtEdges.add(wte);
        }
        
        //Relations
        Element ruleNode = root.element("Relations");
        Iterator<Element> ruleIter = ruleNode.elementIterator(); 
        while (ruleIter.hasNext()) {  
            // 获取某个子节点对象  
            Element tempElement = ruleIter.next();
            WTBiRelation wtb = new WTBiRelation();
            wtb.setRelationId(tempElement.attributeValue("edgeId").trim());
            wtb.setFromNode(tempElement.attributeValue("fromWindowID").trim());
            wtb.setToNode(tempElement.attributeValue("toWindowID").trim());
            windowTree.wtBiRelations.add(wtb);
        }
        
	}
}
