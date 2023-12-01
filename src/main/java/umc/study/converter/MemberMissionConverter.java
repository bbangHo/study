package umc.study.converter;

import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(MissionRequestDTO.changeMissionStatusDTO reqDTO) {
        MissionStatus missionStatus = null;

        switch (reqDTO.getMissionStatus()) {
            case CHALLENGING: missionStatus = MissionStatus.CHALLENGING; break;
            case COMPLET: missionStatus = MissionStatus.COMPLET; break;
        }

        return MemberMission.builder()
                .missionStatus(missionStatus)
                .build();
    }

    public static MissionResponseDTO.changeMissionStatusResultDTO toChangeMissionStatusResult(MemberMission memberMission) {
        return MissionResponseDTO.changeMissionStatusResultDTO.builder()
                .missionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
