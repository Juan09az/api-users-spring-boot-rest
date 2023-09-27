package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class updateContractDto {
    @NotBlank(message = "idContract is mandatory")
    private Long idContract;
    @NotBlank(message = "idUser is mandatory")
    private Integer notificationSetting;
    private boolean paid;

    public updateContractDto() {
    }


    public updateContractDto(Long idContract, Integer notificationSetting) {
        this.idContract = idContract;
        this.notificationSetting = notificationSetting;
    }

    public updateContractDto(Long idContract, boolean paid) {
        this.idContract = idContract;
        this.paid = paid;
    }
}
