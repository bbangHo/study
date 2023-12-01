package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberQueryService;
import umc.study.validation.annotation.IsChallenging;

@Component
@RequiredArgsConstructor
public class MissionIsChallengingValidator implements ConstraintValidator<IsChallenging, Long> {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(IsChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Boolean isValid = memberQueryService.MemberMissionIsChallenging(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();        // 기본 제약 조건 위반을 비활성
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGING.toString()).addConstraintViolation();
            return false;
        }

        return true;
    }
}
