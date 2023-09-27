package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CreateServiceContractDto {
    @NotBlank(message = "frequency is mandatory")
    private String frequency;
    @NotBlank(message = "nameService is mandatory")
    private String nameService;
    @NotBlank(message = "idService is mandatory")
    private Long idService;

    public CreateServiceContractDto() {
    }

    public CreateServiceContractDto(String frequency, String nameService, Long idService) {
        this.frequency = frequency;
        this.nameService = nameService;
        this.idService = idService;
    }
}
