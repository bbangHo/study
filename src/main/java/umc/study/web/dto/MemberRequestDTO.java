package umc.study.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.validation.annotation.ExistCategories;

import java.time.LocalDateTime;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JoinDTO {
        @NotBlank(message = "공백일 수 없습니다.")
        String name;

        @NotNull(message = "1(MALE) 또는 2(FEMALE) 이어야 합니다.")
        @Max(value = 2)
        @Min(value = 1)
        Integer gender;

        @Past(message = "현재 보다 과거 시간이어야 합니다.")
        LocalDateTime brith;

        @Size(min = 5, max = 255, message = "5 ~ 255 글자입니다.")
        String address;

        @Size(min = 5, max = 255, message = "5 ~ 255 글자입니다.")
        String specAddress;

        @ExistCategories
        List<Long> preferCategory;
    }
}
