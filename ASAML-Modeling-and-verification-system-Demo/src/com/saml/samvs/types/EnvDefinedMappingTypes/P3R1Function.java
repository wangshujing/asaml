package com.saml.samvs.types.EnvDefinedMappingTypes;

import java.util.Map;

/**
 * 
 * @author EW
 * Functional Interface that accepts two terms, t1:T1 and t2:T2 
 * and returns one result of type R.
 * This is generic Interface with type T1, T2, T3 and R.
 * 自定义函数式接口，接收一个类型为T1*T2*T3的项，返回类型为R的结果项
 *
 * @param <T>
 */
@FunctionalInterface
public interface P3R1Function<T1, T2, T3, R> {
	public  R lambdaFunction(T1 t1, T2 t2, T3 t3);  
}
