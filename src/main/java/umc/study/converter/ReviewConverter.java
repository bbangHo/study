package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.RequestDTO requestDTO) {
        return Review.builder()
                .content(requestDTO.getContent())
                .score(requestDTO.getScore())
                .build();
    }

    public static ReviewResponseDTO.ResponseDTO toResponseDTO(Review review) {
        return ReviewResponseDTO.ResponseDTO.builder()
                .id(review.getId())
                .createAt(LocalDateTime.now())
                .build();
    }
}
