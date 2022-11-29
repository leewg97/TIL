package com.dev.passweb.repository.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "statistics")
public class StatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statisticsSeq;

    private LocalDateTime statisticsAt; // 일 단위

    private int allCount;

    private int attendedCount;

    private int cancelledCount;

}
