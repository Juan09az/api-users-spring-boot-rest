package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailValuesDto {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String name;
    private String lastname;
    private String tokenPassword;

    public EmailValuesDto() {
    }

    public EmailValuesDto(String mailFrom, String mailTo, String subject, String name, String lastname, String tokenPassword) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.name = name;
        this.lastname=lastname;
        this.tokenPassword = tokenPassword;
    }
}
