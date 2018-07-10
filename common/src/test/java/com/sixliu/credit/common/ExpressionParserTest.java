package com.sixliu.credit.common;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author:MG01867
 * @date:2018年7月10日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class ExpressionParserTest {

	public static void main(String[] agrs) {
		ExpressionParser expressionParser = new SpelExpressionParser();
		Expression expression = expressionParser.parseExpression("[0].value");
		
		int loopCount = 1;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loopCount; i++) {
			Object[] objects = new Object[2];
			ValueOb ob1 = new ValueOb();
			ob1.value = "test1";
			objects[0] = ob1;
			ValueOb ob2 = new ValueOb();
			ob2.value = "test2";
			objects[1] = ob2;
			Object result = expression.getValue(objects);
			System.out.println(result);
		}
		long endTime = System.currentTimeMillis();
		long expressionTime=endTime - startTime;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loopCount; i++) {
			ValueOb[] objects = new ValueOb[2];
			ValueOb ob1 = new ValueOb();
			ob1.value = "test1";
			objects[0] = ob1;
			ValueOb ob2 = new ValueOb();
			ob2.value = "test2";
			objects[1] = ob2;
			Object result =objects[0].value ;
			System.out.println(result);
		}
		endTime = System.currentTimeMillis();
		long nonExpressionTime=endTime - startTime;
		System.out.println("expression time:" + expressionTime);
		System.out.println("non expression time:" + nonExpressionTime);
		
		expression = expressionParser.parseExpression("'Apples'.toUpperCase()");
		Object result = expression.getValue();
		System.out.println(result);
	}

	
	static class ValueOb {
		public String value;

		public String getValue() {
			return value;
		}
	}
}
