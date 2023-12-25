package umc.study.web.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.service.memberService.MemberQueryService;
import umc.study.service.memberService.MemberService;
import umc.study.validation.annotation.CheckPage;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    @PostMapping
    public ApiResponse<MemberResponseDTO.JoinResultDTO> Join(@Valid @RequestBody MemberRequestDTO.JoinDTO joinDTO) {
        return ApiResponse.onSuccess(memberService.join(joinDTO));
    }

    @GetMapping("{memberId}/reviews")
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewList(@Positive @PathVariable(name = "memberId") Long memberId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page) {
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(memberQueryService.getMemberReviews(memberId, --page)));
    }
}
