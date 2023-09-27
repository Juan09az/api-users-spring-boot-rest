package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class updateContractAndRegisterPayment {
    @NotBlank(message = "idContract is mandatory")
    private Long idContract;
    @NotBlank(message = "idPaymentMethod is mandatory")
    private Long idPaymentMethod;
    @NotBlank(message = "paymentDate is mandatory")
    private LocalDateTime paymentDate;
    @NotBlank(message = "paymentValue is mandatory")
    private BigDecimal paymentValue;
    private String annotations;
    private boolean paid;
    @NotBlank(message = "notificationSetting is mandatory")
    private Integer notificationSetting;
    @NotBlank(message = "value is mandatory")
    private BigDecimal value;
    @NotBlank(message = "dueDate is mandatory")
    private LocalDateTime dueDate;
    private String description;

    public updateContractAndRegisterPayment() {
    }

    public updateContractAndRegisterPayment(BigDecimal value, LocalDateTime dueDate, Integer notificationSetting, String description) {
        this.value = null;
        this.dueDate = null;
        this.notificationSetting = null;
        this.description = null;
    }

    public updateContractAndRegisterPayment(Long idContract, Integer notificationSetting) {
        this.idContract = idContract;
        this.notificationSetting = notificationSetting;
    }

    public updateContractAndRegisterPayment(Long idContract, boolean paid) {
        this.idContract = idContract;
        this.paid = paid;
    }
    public updateContractAndRegisterPayment(Long idContract, Long idPaymentMethod, LocalDateTime paymentDate, BigDecimal paymentValue, String annotations, boolean paid) {
        this.idContract = idContract;
        this.idPaymentMethod = idPaymentMethod;
        this.paymentDate = paymentDate;
        this.paymentValue = paymentValue;
        this.annotations = annotations;
        this.paid = paid;
    }
}
