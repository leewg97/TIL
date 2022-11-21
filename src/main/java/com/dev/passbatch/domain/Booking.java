package com.dev.passbatch.domain;

import com.dev.passbatch.constant.BaseEntity;
import com.dev.passbatch.constant.BookingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingSeq;

    private Integer passSeq;

    private String userId;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private boolean usedPass;

    private boolean attended;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private LocalDateTime cancelledAt;

}
