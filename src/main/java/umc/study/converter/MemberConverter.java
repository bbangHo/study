package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toMemberJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO joinDTO) {

        Gender gender = null;

        switch(joinDTO.getGender()) {
            case 1: gender = Gender.MALE; break;
            case 2: gender = Gender.FEMALE; break;
        }

        return Member.builder()
                .name(joinDTO.getName())
                .gender(gender)
                .brith(joinDTO.getBrith())
                .address(joinDTO.getAddress())
                .specAddress(joinDTO.getSpecAddress())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
