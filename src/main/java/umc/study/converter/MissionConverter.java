package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.addMissionDTO reqDTO) {
        return Mission.builder()
                .missionSpec(reqDTO.getMissionSpec())
                .reward(reqDTO.getReward())
                .deadlien(reqDTO.getDeadlien())
                .build();
    }

    public static MissionRequestDTO.addMissionDTO toAddMissionDTO(MissionRequestDTO.requestAddMissionDTO reqDTO, Long storeId) {
        return MissionRequestDTO.addMissionDTO.builder()
                .missionSpec(reqDTO.getMissionSpec())
                .storeId(storeId)
                .reward(reqDTO.getReward())
                .deadlien(reqDTO.getDeadlien())
                .build();
    }

    public static MissionResponseDTO.addMissionResultDTO toResponseDTO (Mission mission) {
        return MissionResponseDTO.addMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionRequestDTO.changeMissionStatusDTO toChangeMissionStatusDTO(MissionRequestDTO.RquestChangeMissionStatusDTO reqDTO,
                                                                                    Long storeId, Long missionId) {
        MissionStatus missionStatus = null;

        switch (reqDTO.getStatusId().intValue()) {
            case 1: missionStatus = MissionStatus.CHALLENGING; break;
            case 2: missionStatus = MissionStatus.COMPLET; break;
        }

        return MissionRequestDTO.changeMissionStatusDTO.builder()
                .storeId(storeId)
                .missionId(missionId)
                .missionStatus(missionStatus)
                .memberId(reqDTO.getMemberId())
                .build();
    }

}
