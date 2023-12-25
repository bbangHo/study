package umc.study.service.memberService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;

public interface MemberQueryService {

    public Boolean existFoodCategory(Long value);

    public Boolean MemberMissionIsChallenging(Long value);

    Page<Review> getMemberReviews(Long memberId, Integer page);
}
