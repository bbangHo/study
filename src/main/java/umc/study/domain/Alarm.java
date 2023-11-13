package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.common.BaseEntity;
import umc.study.domain.enums.AlarmType;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    @Column(nullable = false)
    private Boolean checkStatus;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false)
    private String content;

}
