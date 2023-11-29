package umc.study.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.FoodCategory;
import umc.study.repository.FoodCategoryRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Boolean getFoodCategory(Long value) {
        return foodCategoryRepository.existsById(value);
    }
}
