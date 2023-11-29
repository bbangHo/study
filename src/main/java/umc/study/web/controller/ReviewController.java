package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.ReviewService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    // TODO: 이미지 처리
    @PostMapping
    public ApiResponse<ReviewResponseDTO.ResponseDTO> addStoreReview(@Valid @RequestBody ReviewRequestDTO.RequestDTO requestDTO) {
        return ApiResponse.onSuccess(reviewService.addStoreReview(requestDTO));
    }
}
