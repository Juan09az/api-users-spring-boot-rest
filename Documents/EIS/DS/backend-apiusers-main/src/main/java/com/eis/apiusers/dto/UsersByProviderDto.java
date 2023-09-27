package com.eis.apiusers.dto;

import com.eis.apiusers.entities.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter @Setter
public class UsersByProviderDto {
    @NotBlank(message = "idContract is mandatory")
    private Long idContract;
    @NotBlank(message = "idUser is mandatory")
    private Long idUser;
    @NotBlank(message = "email is mandatory")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "phone is mandatory")
    private String phone;
    @NotBlank(message = "notificationSetting is mandatory")
    private Integer notificationSetting;
    //@NotBlank(message = "idService is mandatory")
    //private Long idService;
    @NotBlank(message = "subscriptionCode is mandatory")
    private String subscriptionCode;
    private String name;
    private String lastname;

    public UsersByProviderDto() {
    }

    public UsersByProviderDto(Long idContract, Long idUser, String email, String phone, Integer notificationSetting, String subscriptionCode, String name, String lastname) {
        this.idContract = idContract;
        this.idUser=idUser;
        this.email = email;
        this.phone = phone;
        this.notificationSetting = notificationSetting;
        this.subscriptionCode = subscriptionCode;
        this.name = name;
        this.lastname = lastname;
    }
}
