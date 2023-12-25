package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    public Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    public Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}
