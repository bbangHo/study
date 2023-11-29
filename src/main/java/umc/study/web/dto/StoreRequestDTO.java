package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.domain.Review;

import java.util.ArrayList;
import java.util.List;

public class StoreRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestDTO{
        @NotBlank
        private String name;

        @NotBlank
        private String specAddress;

        @Positive
        private Long regionNumber;
    }
}
