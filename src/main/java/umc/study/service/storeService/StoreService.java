package umc.study.service.storeService;

import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

public interface StoreService {

    public StoreResponseDTO.CreateStoreResultDTO enrollStore(StoreRequestDTO.StoreDTO storeDTO);

    public StoreResponseDTO.CreateReivewResultDTO addStoreReview(StoreRequestDTO.ReviewDTO requestDTO, Long storeId);
}
