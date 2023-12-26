package umc.study.service.storeService;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MissionRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private StoreRepository storeRepository;
    private ReviewRepository reviewRepository;
    private MissionRepository missionRepository;

    @Override
    public Boolean existStore(Long storeId) {
        return storeRepository.existsById(storeId);
    }

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        Page<Review> reviewPage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return reviewPage;
    }

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        Page<Mission> missionPage =  missionRepository.findAllByStore(store, PageRequest.of(page, 10, Sort.by("deadlien").ascending()));
        return missionPage;
    }
}
