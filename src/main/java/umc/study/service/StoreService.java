package umc.study.service;

import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

public interface StoreService {

    public StoreResponseDTO.ResponseDTO enrollStore(StoreRequestDTO.RequestDTO requestDTO);
}
