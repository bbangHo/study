package umc.study.converter;

import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.RequestDTO requestDTO) {
        return Store.builder()
                .name(requestDTO.getName())
                .address(requestDTO.getSpecAddress())
                .build();
    }

    public static StoreResponseDTO.ResponseDTO toResponseDTO(Store store) {
        return StoreResponseDTO.ResponseDTO.builder()
                .id(store.getId())
                .createAt(LocalDateTime.now())
                .build();
    }
}
