package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.common.BaseEntity;
import umc.study.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false, length = 40)
    private String missionSpec;

    private Integer reward;

    private LocalDate deadlien;     // TODO: 오타, LocalDateTime으로 바꿔야하지 않을까..?

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissionsList = new ArrayList<>();

    public void addStore(Store store) {
        this.store = store;
        store.getMissionList().add(this);
    }
}
