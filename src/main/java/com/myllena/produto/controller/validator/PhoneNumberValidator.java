package com.myllena.produto.controller.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext context) {
        var pattern = "^(55)?([1-9]{2})?(\\d{4,5})(\\d{4})";

        return number.matches(pattern);
    }
}
