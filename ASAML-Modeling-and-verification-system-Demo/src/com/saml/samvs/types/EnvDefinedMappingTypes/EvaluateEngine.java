package com.saml.samvs.types.EnvDefinedMappingTypes;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.saml.samvs.types.EnvDefinedBasicTypes.PageInfo;

public class EvaluateEngine {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//实例化lambda表达式的对象
		LambdaExpressions lambdaExpressions = new LambdaExpressions();
		//初始化，并对其所有声明的lambda表达式的body进行定义，全部采用{statement*;}形式
		lambdaExpressions.initiate();
		//读入每个函数的输入实参
		lambdaExpressions.readParameters();
		//通过求值计算结果是否与预期匹配，其中预期结果已在lmXML中给出
	//	lambdaExpressions.evaluate();
		
		
	}

}
