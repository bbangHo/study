package umc.study.service.storeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.StoreConverter;
import umc.study.domain.Member;
import umc.study.domain.Region;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.RegionRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public StoreResponseDTO.CreateStoreResultDTO enrollStore(StoreRequestDTO.StoreDTO StoreDTO) {
        Store store = StoreConverter.toStore(StoreDTO);

        Region region = regionRepository.findById(StoreDTO.getRegionNumber())
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));

        store.setRegion(region);

        return StoreConverter.toCreateStoreResultDTO(storeRepository.save(store));
    }

    @Override
    public StoreResponseDTO.CreateReivewResultDTO addStoreReview(StoreRequestDTO.ReviewDTO requestDTO, Long storeId) {
        Review review = StoreConverter.toReview(requestDTO);
        Store store = storeRepository.findById(storeId).get();
        Member member = memberRepository.findById(requestDTO.getMemberId()).get();

        review.addMemberAndStore(member, store);

        return StoreConverter.toCreateReviewResultDTO(reviewRepository.save(review));
    }
}
