package com.dev.passbatch.repository.pass;

import com.dev.passbatch.repository.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pass")
public class PassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passSeq;

    private Integer packageSeq;

    private String userId;

    @Enumerated(EnumType.STRING)
    private PassStatus status;

    private Integer remainingCount;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private LocalDateTime expiredAt;

}
