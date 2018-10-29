package com.saml.samvs.types.EnvDefinedMappingTypes;

/**
 * 
 * @author EW
 * Functional Interface that accepts one term of type T.
 * This is generic Interface with type T1 and T2.
 * 自定义函数式接口，接收一个类型为T1*T2的项，无返回参数
 *
 * @param <T>
 */
@FunctionalInterface
public interface P2R0Function<T1, T2> extends TemplateInterface {
	public void lambdaFunction(T1 t1, T2 t2);  
}
