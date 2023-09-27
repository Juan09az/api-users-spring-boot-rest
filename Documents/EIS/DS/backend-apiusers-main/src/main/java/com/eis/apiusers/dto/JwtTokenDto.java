package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtTokenDto {
    private String token;

    public JwtTokenDto() {
    }

    public JwtTokenDto(String token) {
        this.token = token;
    }
}
