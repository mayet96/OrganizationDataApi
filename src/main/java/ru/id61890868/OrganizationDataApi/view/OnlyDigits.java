package ru.id61890868.OrganizationDataApi.view;


import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OnlyDigitsValidator.class)
public @interface OnlyDigits {
    String message() default "{OnlyDigits.NotValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
