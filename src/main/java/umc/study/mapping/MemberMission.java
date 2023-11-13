package umc.study.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.study.common.BaseEntity;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;

    public void addMember(Member member) {
        this.member = member;
        member.getMemberMissionsList().add(this);
    }

    public void addMission(Mission mission) {
        this.mission = mission;
        mission.getMemberMissionsList().add(this);
    }
}
