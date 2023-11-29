package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public StoreResponseDTO.ResponseDTO enrollStore(StoreRequestDTO.RequestDTO requestDTO) {
        Store store = StoreConverter.toStore(requestDTO);

        Region region = regionRepository.findById(requestDTO.getRegionNumber())
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));

        store.setRegion(region);

        return StoreConverter.toResponseDTO(storeRepository.save(store));
    }
}
