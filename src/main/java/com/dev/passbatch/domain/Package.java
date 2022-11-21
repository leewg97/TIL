package com.dev.passbatch.domain;

import com.dev.passbatch.constant.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "package")
public class Package extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageSeq;

    private String packageName;

    private Integer count;

    private Integer period;

}
