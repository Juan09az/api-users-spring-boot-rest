package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReceiveContractDto {
    private Long id_provider;
    private String apiKey;
    private Long id_contract;
    private BigDecimal value;
    private LocalDateTime due_date;
    private Integer notification_setting;

    public ReceiveContractDto(Long id_provider, String apiKey, Long id_contract, BigDecimal value, LocalDateTime due_date, Integer notification_setting) {
        this.id_provider = id_provider;
        this.apiKey = apiKey;
        this.id_contract = id_contract;
        this.value = value;
        this.due_date = due_date;
        this.notification_setting = notification_setting;
    }
}
