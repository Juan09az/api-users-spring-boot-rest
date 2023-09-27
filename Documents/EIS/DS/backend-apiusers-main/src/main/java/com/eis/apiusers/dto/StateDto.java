package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class StateDto {
    @NotBlank(message = "idState is mandatory")
    private Long idState;
    @NotBlank(message = "nameState is mandatory")
    private String nameState;

    public StateDto() {
    }

    public StateDto(Long idState, String nameState) {
        this.idState = idState;
        this.nameState = nameState;
    }
}