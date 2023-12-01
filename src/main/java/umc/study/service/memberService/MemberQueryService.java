package umc.study.service.memberService;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Review;

public interface MemberQueryService {

    public Boolean existFoodCategory(Long value);

    public Boolean MemberMissionIsChallenging(Long value);

    public Page<Review> getMemberReviews(Long memberId, Integer page);

    public Page<Mission> getMemberMissions(Long memberId, Integer page);
}
