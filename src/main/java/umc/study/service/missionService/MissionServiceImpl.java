package umc.study.service.missionService;

import jakarta.transaction.Transactional;
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
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MissionResponseDTO.addMissionResultDTO addMission(MissionRequestDTO.addMissionDTO addMissionDTO) {
        Mission mission = MissionConverter.toMission(addMissionDTO);
        Store store = storeRepository.findById(addMissionDTO.getStoreId()).get();

        mission.addStore(store);

        return MissionConverter.toResponseDTO(missionRepository.save(mission));
    }

    @Override
    @Transactional
    public MissionResponseDTO.changeMissionStatusResultDTO changeMissionStatus(MissionRequestDTO.changeMissionStatusDTO cmsDTO) {
        MemberMission memberMission = memberMissionRepository.getMemberMissionByMissionIdAndMemberId(cmsDTO.getMissionId(), cmsDTO.getMemberId());
        Member member = memberMission.getMember();
        Mission mission = memberMission.getMission();

        memberMission.addMemberAndMission(member, mission);
        memberMission.changeMissionStatus(cmsDTO.getMissionStatus());

        memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toChangeMissionStatusResult(mission);
    }
}
