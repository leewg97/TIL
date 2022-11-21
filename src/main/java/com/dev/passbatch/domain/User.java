package com.dev.passbatch.domain;

import com.dev.passbatch.constant.BaseEntity;
import com.dev.passbatch.constant.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    private String userId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String phone;

    private String meta;

}
