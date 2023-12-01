package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {


    public MemberMission getMemberMissionByMissionIdAndMemberId(Long missionId, Long memberId);
}
