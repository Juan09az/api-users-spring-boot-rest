package com.eis.apiusers.entities.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtRequest {

    private String email;
    private String password;

    public JwtRequest(){}
    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
