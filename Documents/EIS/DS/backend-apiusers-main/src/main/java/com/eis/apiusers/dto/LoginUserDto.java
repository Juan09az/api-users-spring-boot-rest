package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter @Setter
public class LoginUserDto {
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "email is mandatory")
    private String password;

    public LoginUserDto() {
    }

    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
