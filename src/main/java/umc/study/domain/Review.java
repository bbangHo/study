package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.common.BaseEntity;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String content;

    private Integer score;

    public void addMember(Member member) {
        this.member = member;
        member.getReviewList().add(this);
    }

    public void addStore(Store store) {
        this.store = store;
        store.getReviewList().add(this);
    }

    public void addMemberAndStore(Member member, Store store) {
        this.store = store;
        this.member = member;
        store.getReviewList().add(this);
        member.getReviewList().add(this);
    }
}
