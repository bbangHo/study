package umc.study.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestDTO {
        @Positive
        private Long memberId;

        @ExistStore
        private Long storeId;

        @NotBlank
        private String content;

        @Max(value = 5)
        @Min(value = 1)
        private Integer score;
    }
}
