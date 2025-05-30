package com.tien.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Constraint(validatedBy = ValidEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Email đã tồn tại. Vui lòng nhập lại";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
