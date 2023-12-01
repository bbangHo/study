package umc.study.web.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import umc.study.domain.enums.MissionStatus;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class requestAddMissionDTO {
        @Column(nullable = false, length = 40)
        private String missionSpec;

        @DecimalMin(value = "1", message = "최소 1원이상 이어야 합니다")
        private Integer reward;

        @Future(message = "현재보다 미래의 시간이어야 합니다.")
        @DateTimeFormat(pattern = "yyyy-mm-dd")
        private LocalDate deadlien;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class addMissionDTO {
        private Long storeId;

        private String missionSpec;

        private Integer reward;

        private LocalDate deadlien;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RquestChangeMissionStatusDTO {
        @Positive
        @DecimalMin(value = "1")
        @DecimalMax(value = "2")
        private Long statusId;        // 1: challenging, 2: complete
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class changeMissionStatusDTO {
        private Long memberId;

        private Long missionId;

//        private Long storeId;

        private MissionStatus missionStatus;        // 1: challenging, 2: complete

//        public void setMissionIdAndStoreId(Long missionId, Long storeId) {
//            this.missionId = missionId;
//            this.storeId = storeId;
//        }
    }
}
