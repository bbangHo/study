package umc.study.service;

import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

public interface MissionService {

    public MissionResponseDTO.addMissionResultDTO addMission(MissionRequestDTO.addMissionDTO addMissionDTO);

    public MissionResponseDTO.changeMissionStatusResultDTO changeMissionStatus(MissionRequestDTO.changeMissionStatusDTO changeMissionStatusDTO);
}
