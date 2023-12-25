package umc.study.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreDTO {
        @NotBlank
        private String name;

        @NotBlank
        private String specAddress;

        @Positive
        private Long regionNumber;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewDTO {
        @Positive
        private Long memberId;

        @NotBlank
        private String content;

        @Max(value = 5)
        @Min(value = 1)
        private Integer score;
    }
}
