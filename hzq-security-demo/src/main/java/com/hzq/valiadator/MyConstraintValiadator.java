package com.hzq.valiadator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义参数校验注解
 */
public class MyConstraintValiadator implements ConstraintValidator<MyConstraint,Object> {

    //TODO 可以注入自己需要Service
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(value);
        return true;
    }
}
