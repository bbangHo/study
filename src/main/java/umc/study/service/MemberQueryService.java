package umc.study.service;

import umc.study.domain.mapping.MemberMission;

public interface MemberQueryService {

    public Boolean existFoodCategory(Long value);

    public Boolean MemberMissionIsChallenging(Long value);
}
