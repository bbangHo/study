package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    public MemberMission getMemberMissionByMissionIdAndMemberId(Long missionId, Long memberId);

    public Page<MemberMission> findAllByMemberAndMissionStatus(Member member, MissionStatus missionStatus, Pageable pageable);

}
