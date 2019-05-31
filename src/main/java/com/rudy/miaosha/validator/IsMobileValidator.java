package com.rudy.miaosha.validator;


import com.rudy.miaosha.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class IsMobileValidator implements ConstraintValidator<isMobile, String> {

   private boolean isEmpty = true;

    @Override
    public void initialize(isMobile isMobile) {
        isEmpty = isMobile.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (isEmpty){
            // 如果不许为空
            boolean isMobile = ValidatorUtil.isMobile(s);
            return isMobile;
        }else {
            // 可以为空
            if (StringUtils.isBlank(s)){
                return true;
            }
            return ValidatorUtil.isMobile(s);
        }
    }
}
