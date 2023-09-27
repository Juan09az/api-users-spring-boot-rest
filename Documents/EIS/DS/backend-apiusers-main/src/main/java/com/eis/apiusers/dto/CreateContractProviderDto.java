package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class CreateContractProviderDto {
    @NotBlank(message = "idUser is mandatory")
    private Long idUser;
    @NotBlank(message = "type is mandatory")

    private String type;
    @NotBlank(message = "description is mandatory")

    private String description;
    @NotBlank(message = "idProvider is mandatory")

    private Long idProvider;
    @NotBlank(message = "subscriptionCode is mandatory")

    private String subscriptionCode;

    public CreateContractProviderDto() {
    }

    public CreateContractProviderDto(Long idUser, String type, String description, Long idProvider, String subscriptionCode) {
        this.idUser = idUser;
        this.type = type;
        this.description = description;
        this.idProvider = idProvider;
        this.subscriptionCode = subscriptionCode;
    }
}