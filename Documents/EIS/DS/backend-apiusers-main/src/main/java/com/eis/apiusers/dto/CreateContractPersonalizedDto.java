package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class CreateContractPersonalizedDto {
    @NotBlank(message = "idUser is mandatory")
    private Long idUser;
    @NotBlank(message = "type is mandatory")
    private String type;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotBlank(message = "idService is mandatory")
    private Long idService;
    @NotBlank(message = "frequency is mandatory")
    private String frequency;
    @NotBlank(message = "nameService is mandatory")
    private String nameService;
    @NotBlank(message = "value is mandatory")
    private BigDecimal value;
    @NotBlank(message = "dueDate is mandatory")
    private LocalDateTime dueDate;
    @NotBlank(message = "notificationSetting is mandatory")
    private Integer notificationSetting;

    public CreateContractPersonalizedDto() {
    }

    public CreateContractPersonalizedDto(Long idUser, String type, String description, Long idService, String frequency, String nameService, BigDecimal value, LocalDateTime dueDate, Integer notificationSetting) {
        this.idUser = idUser;
        this.type = type;
        this.description = description;
        this.idService = idService;
        this.frequency = frequency;
        this.nameService = nameService;
        this.value = value;
        this.dueDate = dueDate;
        this.notificationSetting = notificationSetting;
    }
}
