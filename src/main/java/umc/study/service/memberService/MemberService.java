package umc.study.service.memberService;

import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

public interface MemberService {

    public MemberResponseDTO.JoinResultDTO join(MemberRequestDTO.JoinDTO joinDTO);
}
