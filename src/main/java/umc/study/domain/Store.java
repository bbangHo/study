package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private String address;

    private Float score;

    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();
}
