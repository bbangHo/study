package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.ReviewService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    // TODO: 이미지 처리
    @PostMapping("store/{storeId}/review")
    public ApiResponse<ReviewResponseDTO.ResponseDTO> addStoreReview(@Valid @RequestBody ReviewRequestDTO.RequestDTO requestDTO,
                                                                     @RequestParam(name = "storeId") Long storeId) {
        return ApiResponse.onSuccess(reviewService.addStoreReview(requestDTO));
    }
}
