package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.service.storeService.StoreQueryService;
import umc.study.service.storeService.StoreService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("store")
public class StoreController {
    private final StoreService storeService;
    private final StoreQueryService storeQueryService;

    @PostMapping
    public ApiResponse<StoreResponseDTO.CreateStoreResultDTO> enrollStore(@Valid @RequestBody StoreRequestDTO.StoreDTO storeDTO) {
        return ApiResponse.onSuccess(storeService.enrollStore(storeDTO));
    }

    // TODO: 이미지 처리
    @PostMapping("{storeId}/review")
    public ApiResponse<StoreResponseDTO.CreateReivewResultDTO> addStoreReview(@Valid @RequestBody StoreRequestDTO.ReviewDTO reviewDTO,
                                                                     @RequestParam(name = "storeId") Long storeId) {
        return ApiResponse.onSuccess(storeService.addStoreReview(reviewDTO, storeId));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(
            summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지는 1 부터 시작입니다.")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewPage = storeQueryService.getReviewList(storeId, --page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewPage));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API",
            description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지는 1 부터 시작입니다.")
    })
    public ApiResponse<StoreResponseDTO.MissionListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                       @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionPage = storeQueryService.getMissionList(storeId, --page);
        return ApiResponse.onSuccess(StoreConverter.missionListDTO(missionPage));
    }


}
