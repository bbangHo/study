package umc.study.service.memberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.*;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Slf4j
public class MemberQueryServiceImpl implements MemberQueryService {

    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Boolean existFoodCategory(Long value) {
        return foodCategoryRepository.existsById(value);
    }


    @Override
    public Boolean MemberMissionIsChallenging(Long missionId) {
        // missionId
        Optional<Long> memberMissionIdOptional = missionRepository.findById(missionId)
                .map(mission -> mission.getMemberMissionsList().stream()
                        .filter(memberMission -> memberMission.getId().equals(missionId))
                        .map(MemberMission::getId)
                        .findFirst()
                )
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = memberMissionRepository.findById(memberMissionIdOptional.get()).orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_MISSION_NOT_FOUND));

        return memberMission.getMissionStatus().equals(MissionStatus.CHALLENGING) ? false : true;
    }

    @Override
    public Page<Review> getMemberReviews(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        Page<Review> reviewList = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));

        return reviewList;
    }
}
