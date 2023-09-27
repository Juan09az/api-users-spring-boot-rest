package com.eis.apiusers.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Getter @Setter
public class CreateUserDto {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Username is mandatory")
    private String password;
    private String name;
    private String lastname;
    @NotBlank(message = "Phone is mandatory")
    private String phone;
    private boolean enabled=true;
    private Long idState;

    public CreateUserDto() {
    }

    public CreateUserDto(String email, String password, String name, String lastname, String phone, boolean enabled, Long idState) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.enabled = enabled;
        this.idState = idState;
    }
}
