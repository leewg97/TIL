package com.won.tutorial.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)    // @Valid 관련 어노테이션
    private String username;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}