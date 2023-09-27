package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ContractsByProviderDto {
    @NotBlank(message = "idProvider is mandatory")
    private Long idProvider;
    @NotBlank(message = "apikey is mandatory")
    private String apikey;

    public ContractsByProviderDto() {
    }

    public ContractsByProviderDto(Long idProvider, String apikey) {
        this.idProvider = idProvider;
        this.apikey = apikey;
    }
}
