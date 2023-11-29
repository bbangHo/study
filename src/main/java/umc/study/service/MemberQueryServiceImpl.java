package umc.study.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.repository.FoodCategoryRepository;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Boolean existFoodCategory(Long value) {
        return foodCategoryRepository.existsById(value);
    }
}
