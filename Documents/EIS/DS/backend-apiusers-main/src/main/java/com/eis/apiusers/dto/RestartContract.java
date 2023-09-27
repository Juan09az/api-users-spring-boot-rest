package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class RestartContract {
    @NotBlank(message = "value is mandatory")
    private BigDecimal value;
    @NotBlank(message = "dueDate is mandatory")
    private LocalDateTime dueDate;
    @NotBlank(message = "notificationSetting is mandatory")
    private Integer notificationSetting;
    private String description;

    public RestartContract() {
    }

    public RestartContract(BigDecimal value, LocalDateTime dueDate, Integer notificationSetting, String description) {
        this.value = value;
        this.dueDate = dueDate;
        this.notificationSetting = notificationSetting;
        this.description = description;
    }
}