package ru.id61890868.OrganizationDataApi.view;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyDigitsValidator implements ConstraintValidator<OnlyDigits, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.equals("")) {
            return false;
        }
        String regex = "[0-9]+";
        return s.matches(regex);
    }
}
