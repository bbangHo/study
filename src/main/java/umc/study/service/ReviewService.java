package umc.study.service;

import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

public interface ReviewService {

    public ReviewResponseDTO.ResponseDTO addStoreReview(ReviewRequestDTO.RequestDTO requestDTO);
}
