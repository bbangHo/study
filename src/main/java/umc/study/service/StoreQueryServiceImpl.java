package umc.study.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.repository.StoreRepository;

@Service
@AllArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private StoreRepository storeRepository;

    @Override
    public Boolean existStore(Long storeId) {
        return storeRepository.existsById(storeId);
    }
}
