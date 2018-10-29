package com.saml.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.saml.util.Printer;
import com.saml.util.WindowTree;
import com.saml.util.XMLParser;

public class SAMVSController {
	
	public static ArrayList<String> generatePath(String path) {
		WindowTree windowTree = new WindowTree();
		path = new FileController().readLocalFilePathConfig("localFilePath")+path;
		path=path.replaceAll("/", "\\\\\\\\");; // 将/换成\ 
		try {
			XMLParser.getXMLWindowTree(windowTree,path);
		} catch (Exception e) {
			System.out.println("WindowTree Parsing Error.");
		}
		System.out.println("WindowTree Parsing Succeed.");
		windowTree.reajustWT();
		windowTree.printWindowTree();
		windowTree.GenTCSet();
		Printer.printList(windowTree.getTestcases(), "TestCases");
		return windowTree.getTestcases();
	}
	
}
