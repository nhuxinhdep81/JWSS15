package com.tien.validation;

import com.tien.service.ResumeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Autowired
    private ResumeService resumeService;


    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return resumeService.checkEmailExist(email) == null;
    }
}
