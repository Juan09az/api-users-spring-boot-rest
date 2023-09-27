package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class PaymentMethodDto {
    @NotBlank(message = "idPaymentmethods is mandatory")
    private Long idPaymentmethods;
    @NotBlank(message = "nameMethod is mandatory")
    private String nameMethod;

    public PaymentMethodDto() {
    }

    public PaymentMethodDto(Long idPaymentmethods, String nameMethod) {
        this.idPaymentmethods = idPaymentmethods;
        this.nameMethod = nameMethod;
    }
}