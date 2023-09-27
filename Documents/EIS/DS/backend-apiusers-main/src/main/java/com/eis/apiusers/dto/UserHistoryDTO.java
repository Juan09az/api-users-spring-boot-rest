package com.eis.apiusers.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class UserHistoryDTO {
        private String email;
        private String name;
        private String lastname;
        private String state;
        private Long paymentId;
        private LocalDateTime paymentDate;
        private String paymentMethod;
        private BigDecimal paymentValue;
        private Long contractId;
        private String contractType;

        public UserHistoryDTO(String email, String name, String lastname, String state, Long paymentId, LocalDateTime paymentDate, String paymentMethod, BigDecimal paymentValue, Long contractId, String contractType) {
            this.email = email;
            this.name = name;
            this.lastname = lastname;
            this.state = state;
            this.paymentId = paymentId;
            this.paymentDate = paymentDate;
            this.paymentMethod = paymentMethod;
            this.paymentValue = paymentValue;
            this.contractId = contractId;
            this.contractType = contractType;
        }

        public UserHistoryDTO() {
        }
}
