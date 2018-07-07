package com.sixliu.credit.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;


/**
*@author:MG01867
*@date:2018年7月7日
*@E-mail:359852326@qq.com
*@version:
*@describe 贷款金额校验:可以为null,否则必须为>=1
*/
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoanAmountValidation.LoanAmountChecker.class)
@Documented
public @interface LoanAmountValidation {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value() default "";

	boolean inclusive() default true;

	public class LoanAmountChecker implements ConstraintValidator<LoanTermTypeValidation, Double> {

		@Override
		public boolean isValid(Double value, ConstraintValidatorContext context) {
			if(null==value) {
				return true;
			}
			return value>=1;
		}

	}
}
