package com.dev.passweb.service.user;

import com.dev.passweb.repository.user.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    private String userId;

    private String userName;

    private UserStatus status;

}
