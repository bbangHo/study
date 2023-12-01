package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.enums.MissionStatus;
import umc.study.service.memberService.MemberQueryService;
import umc.study.service.memberService.MemberService;
import umc.study.service.missionService.MissionService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistStore;
import umc.study.validation.annotation.IsChallenging;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;
    private final MissionService missionService;

    @PostMapping
    public ApiResponse<MemberResponseDTO.JoinResultDTO> Join(@Valid @RequestBody MemberRequestDTO.JoinDTO joinDTO) {
        return ApiResponse.onSuccess(memberService.join(joinDTO));
    }

    @GetMapping("{memberId}/reviews")
    @Operation(
            summary = "특정 회원이 작성한 리뷰 목록 조회 API",
            description = "특정 회원이 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지는 1부터 시작입니다.")
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewList(@Positive @PathVariable(name = "memberId") Long memberId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewPage = memberQueryService.getMemberReviews(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewPage));
    }

    @GetMapping("{memberId}/missions")
    @Operation(
            summary = "특정 회원이 진행중인 미션 목록 조회 API",
            description = "특정 회원이 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지는 1부터 시작입니다.")
    })
    public ApiResponse<MemberResponseDTO.MissionListDTO> getMissionList(/*@ExistMember*/ @Positive @PathVariable(name = "memberId") Long memberId,
                                                                        @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionPage = memberQueryService.getMemberMissions(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.missionListDTO(missionPage));
    }

    @PatchMapping("{memberId}/mission/{missionId}/status")
    @Operation(
            summary = "특정 회원이 진행중인 미션의 상태를 변경하는 API",
            description = "특정 회원이 진행중인 미션의 상태를 변경하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "미션 아이디, path variable 입니다!"),
            @Parameter()
    })
    public MissionResponseDTO.changeMissionStatusResultDTO changeMissionStatus(@Valid @RequestBody MissionRequestDTO.RquestChangeMissionStatusDTO reqDTO,
                                                                               @Positive @RequestParam(name = "memberId") Long memberId,
                                                                               @RequestParam(name = "missionId") Long missionId) {

        MissionRequestDTO.changeMissionStatusDTO changeMissionStatusDTO = MissionConverter.toChangeMissionStatusDTO(reqDTO, memberId, missionId);

        return missionService.changeMissionStatus(changeMissionStatusDTO);
    }
}
