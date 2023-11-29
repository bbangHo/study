package umc.study.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private StoreRepository storeRepository;
    private MemberRepository memberRepository;

    @Override
    public ReviewResponseDTO.ResponseDTO addStoreReview(ReviewRequestDTO.RequestDTO requestDTO) {
        Review review = ReviewConverter.toReview(requestDTO);
        Store store = storeRepository.findById(requestDTO.getStoreId()).get();
        Member member = memberRepository.findById(requestDTO.getMemberId()).get();

        review.addMemberAndStore(member, store);

        return ReviewConverter.toResponseDTO(reviewRepository.save(review));
    }
}
