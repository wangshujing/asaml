package com.saml.samvs.types.EnvDefinedMappingTypes;

/**
 * 
 * @author EW
 * Functional Interface that accepts one term of type T.
 * This is generic Interface with type T.
 * 自定义函数式接口，接收一个类型为T的项，无返回参数
 *
 * @param <T>
 */
@FunctionalInterface
public interface P1R0Function<T> extends TemplateInterface {
	public void lambdaFunction(T t);  
}
