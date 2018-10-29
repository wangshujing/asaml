package com.saml.samvs.types.EnvDefinedMappingTypes;

/**
 * 
 * @author EW
 * Functional Interface that accepts one term of type T 
 * and returns one result of type R.
 * This is generic Interface with type T and type R.
 * 自定义函数式接口，接收一个类型为T的项，返回类型为R的结果项
 *
 * @param <T>
 */
@FunctionalInterface
public interface P1R1Function<T, R> extends TemplateInterface  {
	public R lambdaFunction(T t);  
	
}
