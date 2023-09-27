package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class RegisterPaymentDto {
    private Long idContract;
    private Long idPaymentMethod;
    private LocalDateTime paymentDate;
    private BigDecimal paymentValue;
    private String annotations;

    public RegisterPaymentDto() {
    }

    public RegisterPaymentDto(Long idContract, Long idPaymentMethod, LocalDateTime paymentDate, BigDecimal paymentValue, String annotations) {
        this.idContract = idContract;
        this.idPaymentMethod = idPaymentMethod;
        this.paymentDate = paymentDate;
        this.paymentValue = paymentValue;
        this.annotations = annotations;
    }
}