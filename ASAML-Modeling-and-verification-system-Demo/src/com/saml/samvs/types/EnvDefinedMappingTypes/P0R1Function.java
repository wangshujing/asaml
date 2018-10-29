package com.saml.samvs.types.EnvDefinedMappingTypes;

/**
 * 
 * @author EW
 * Functional Interface that accepts no term 
 * and returns one result of type R.
 * This is generic Interface with type R.
 * 自定义函数式接口，没有输入参数，返回类型为R的结果项
 *
 * @param <T>
 */
@FunctionalInterface
public interface P0R1Function<R> extends TemplateInterface  {
	public R lambdaFunction();  
}
