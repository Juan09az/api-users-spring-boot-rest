package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserManagementDto {
    @NotBlank(message = "idUser is mandatory")
    private Long idUser;
    @NotBlank(message = "idState is mandatory")
    private Long idState;
    @NotBlank(message = "lastname is mandatory")
    private String lastname;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "phone is mandatory")
    private String phone;
    @NotBlank(message = "enabled is mandatory")
    private boolean enabled;

    public UserManagementDto() {
    }

    public UserManagementDto(Long idUser, Long idState, String lastname, String name, String email, String phone, boolean enabled) {
        this.idUser = idUser;
        this.idState = idState;
        this.lastname = lastname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
    }
}
