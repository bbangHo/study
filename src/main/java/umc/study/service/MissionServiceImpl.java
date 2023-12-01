package umc.study.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.MissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@Service
@AllArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResponseDTO.addMissionResultDTO addMission(MissionRequestDTO.addMissionDTO addMissionDTO) {
        Mission mission = MissionConverter.toMission(addMissionDTO);
        Store store = storeRepository.findById(addMissionDTO.getStoreId()).get();

        mission.addStore(store);

        return MissionConverter.toResponseDTO(missionRepository.save(mission));
    }

    @Override
    public MissionResponseDTO.changeMissionStatusResultDTO changeMissionStatus(MissionRequestDTO.changeMissionStatusDTO cmsDTO) {
        MemberMission memberMission = memberMissionRepository.getMemberMissionByMissionIdAndMemberId(cmsDTO.getMissionId(), cmsDTO.getMemberId());
        Member member = memberRepository.findById(cmsDTO.getMemberId()).orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(cmsDTO.getMissionId()).orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        memberMission.addMemberAndMission(member, mission);
        memberMission.changeMissionStatus(cmsDTO.getMissionStatus());

        return MemberMissionConverter.toChangeMissionStatusResult(memberMissionRepository.save(memberMission));
    }
}
