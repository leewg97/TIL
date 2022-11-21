package com.dev.passbatch.domain;

import com.dev.passbatch.constant.BaseEntity;
import com.dev.passbatch.constant.PassStatus;
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
public class Pass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passSeq;

    private Integer packageSeq;

    private String userId;

    private PassStatus status;

    private Integer remainingCount;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private LocalDateTime expiredAt;

}
