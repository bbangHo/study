package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.validation.annotation.CheckPage;

@Component
@RequiredArgsConstructor
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValied = value > 0;

        if(!isValied) {
            context.disableDefaultConstraintViolation();        // 기본 제약 조건 위반을 비활성
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NUMBER_BAD_REQUEST.toString()).addConstraintViolation();
            return false;
        }

        return true;
    }
}
