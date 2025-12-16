package com.tcsr.framework.common.validate;

import com.tcsr.framework.common.utils.SpringUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Set;

/**
 * @author tangzhong
 * @since  2025-10-14 11:07
 */
public class BeanValidator {

    private BeanValidator(){}

    private static Validator VALIDATOR;

    public static void validate(Object object, Class<?>... groups) throws ConstraintViolationException {
        if(VALIDATOR == null){
            VALIDATOR = SpringUtils.getBean(Validator.class);
        }
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

}
