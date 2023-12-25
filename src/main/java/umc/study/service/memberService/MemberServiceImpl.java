package umc.study.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberPreferConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.Member;
import umc.study.domain.mapping.MemberPrefer;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MemberPreferRepository;
import umc.study.repository.MemberRepository;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberPreferRepository memberPreferRepository;

    @Override
    @Transactional
    public MemberResponseDTO.JoinResultDTO join(MemberRequestDTO.JoinDTO joinDTO) {
        Member member = MemberConverter.toMember(joinDTO);

        List<FoodCategory> foodCategoryList = joinDTO.getPreferCategory().stream()
                        .map(foodCategory -> {
                            // .map에서의 return은 각 요소에 대해 새로운 값을 반환하는 기능
                            return foodCategoryRepository.findById(foodCategory).get();
                        }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // 양방향 연관 관계 설정은 converter 보다는 service에서 하는 것이 좋음
        memberPreferList.forEach(memberPrefer -> {
            memberPrefer.addMember(member);
            memberPreferRepository.save(memberPrefer);      // 영속화 시키지 않고 member에 넣어서 flush 하니까 에러뜸
        });

        return MemberConverter.toMemberJoinResultDTO(memberRepository.save(member));
    }
}
