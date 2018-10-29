package com.saml.samvs.types.EnvDefinedMappingTypes;

public interface TemplateInterface {
	
	public default void printLambdaFunction(String s) {
		System.out.println("This is the functional interface - " + s);
	}
	
	
}
