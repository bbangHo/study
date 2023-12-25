package umc.study.service.storeService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    public Boolean existStore(Long storeId);

    Optional<Store> findStore(Long id);

    Page<Review> getReviewList(Long StoreId, Integer page);
}
