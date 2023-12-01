package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.service.MissionService;
import umc.study.validation.annotation.ExistStore;
import umc.study.validation.annotation.IsChallenging;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MissionController {

    private final MissionService missionService;

    @PostMapping("store/{storeId}/mission")
    public MissionResponseDTO.addMissionResultDTO addMission(@Valid @RequestBody MissionRequestDTO.requestAddMissionDTO reqDTO,
                                                           @ExistStore @RequestParam(name = "storeId") Long storeId) {

        MissionRequestDTO.addMissionDTO addMissionDTO = MissionConverter.toAddMissionDTO(reqDTO, storeId);
        return missionService.addMission(addMissionDTO);
    }

    @PatchMapping("store/{storeId}/mission/{missionId}/status")
    public MissionResponseDTO.changeMissionStatusResultDTO changeMissionStatus(@Valid @RequestBody MissionRequestDTO.RquestChangeMissionStatusDTO reqDTO,
                                                                               @ExistStore @RequestParam(name = "storeId") Long storeId,
                                                                               @IsChallenging @RequestParam(name = "missionId") Long missionId) {

        MissionRequestDTO.changeMissionStatusDTO changeMissionStatusDTO = MissionConverter.toChangeMissionStatusDTO(reqDTO, storeId, missionId);

        return missionService.changeMissionStatus(changeMissionStatusDTO);
    }

}
