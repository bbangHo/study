package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponseDTO {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addMissionResultDTO {
        Long missionId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class changeMissionStatusResultDTO {
        Long missionId;
        LocalDateTime updatedAt;
    }
}
