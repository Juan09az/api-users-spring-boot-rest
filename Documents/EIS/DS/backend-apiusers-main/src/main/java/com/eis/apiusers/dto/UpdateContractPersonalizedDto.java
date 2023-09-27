package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class UpdateContractPersonalizedDto {
    private Long idContract;
    private Long idService;
    private String frequency;
    private BigDecimal value;
    private LocalDateTime dueDate;
    private String description;
    private String nameService;
    private Integer notificationSetting;

    public UpdateContractPersonalizedDto(Long idContract, Long idService, String frequency, BigDecimal value, LocalDateTime dueDate, String description, String nameService, Integer notificationSetting) {
        this.idContract = idContract;
        this.idService = idService;
        this.frequency = frequency;
        this.value = value;
        this.dueDate = dueDate;
        this.description = description;
        this.nameService = nameService;
        this.notificationSetting = notificationSetting;
    }

    public UpdateContractPersonalizedDto() {
    }
}
