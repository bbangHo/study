package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.StoreDTO reviewDTO) {
        return Store.builder()
                .name(reviewDTO.getName())
                .address(reviewDTO.getSpecAddress())
                .build();
    }

    public static StoreResponseDTO.CreateReivewResultDTO toCreateReviewResultDTO(Review review) {
        return StoreResponseDTO.CreateReivewResultDTO.builder()
                .reivewId(review.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDTO request){
        return Review.builder()
//                .title(request.getTitle())
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }

    public static StoreResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store){
        return StoreResponseDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .body(review.getContent())
                .createdAt(LocalDate.now())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewListDTO = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewPreViewListDTO)
                .listSize(reviewList.getSize())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .build();
    }

    public static StoreResponseDTO.MissionDTO missionDTO(Mission mission){
        return StoreResponseDTO.MissionDTO.builder()
                .storeId(mission.getStore().getId())
                .mission_spec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadlien())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDTO.MissionListDTO missionListDTO(Page<Mission> missionList){
        List<StoreResponseDTO.MissionDTO> missionListDTO = missionList.stream()
                .map(StoreConverter::missionDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionListDTO.builder()
                .missionList(missionListDTO)
                .listSize(missionList.getSize())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }
}
