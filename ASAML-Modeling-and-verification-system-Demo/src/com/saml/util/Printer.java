package com.saml.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

import com.saml.samvs.types.EnvDefinedMappingTypes.TemplateInterface;



/**
 * @author Administrator
 * 提供各种打印功能的类
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class Printer {

	/**
	 * 打印开始标记
	 */
	public static void printStart(){
		System.out.println("----------------------  <begin>  ------------------------------");
	}
	/**
	 * 打印结束标记
	 */
	public static void printEnd(){
		System.out.println("----------------------  <end>  ---------------------------------");
	}
	
	/**
	 * Can't print�?
	 */
	public static void printNothing(){
		System.out.println("Nothing can be printed.");
	}
	
	public static void printIndentString(int indentCount){
		for (int i = 1; i <= indentCount; i++) {
			System.out.print("|--");
		}
	}

	/**
	 * @param aList 即将打印的ArrayList序列
	 * @param listName 序列名称
	 */
	public static void printList(ArrayList aList, String listName){
		Printer.printStart();
		if (aList != null) {
			System.out.println("<" + listName + "序列>, 长度=" + aList.size());
			Iterator it = aList.iterator();
			while(it.hasNext()){
				Object temp = it.next();
				System.out.println(temp);
			}
		} else {
			Printer.printNothing();
		}
		Printer.printEnd();
	}

	/**
	 * @param aQueue 即将打印的ArrayDeque序列
	 * @param qName 队列名称
	 */
	public static void printQueue(ArrayDeque aQueue, String qName){
		Printer.printStart();
		if (aQueue != null) {
			System.out.println("<" + qName + "序列>, 长度 =" + aQueue.size());
			Iterator it = aQueue.iterator();
			while(it.hasNext()){
				Object temp = it.next();
				System.out.println(temp);
			}
		} else {
			Printer.printNothing();
		}
		Printer.printEnd();
	}
	
	/**
	 * @param mName 即将打印的方法名
	 * @param method 方法
	 */
	public static void printMethod(String mName, TemplateInterface m){
		Printer.printStart();
		if (m != null) {
			System.out.println("<" + mName + "方法>, 定义 =" + m.toString());
		} else {
			Printer.printNothing();
		}
		Printer.printEnd();
	}
}
