package com.eis.apiusers.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter @Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment", nullable = false, unique = true)
    private Long idPayment;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    private String annotations;
    @Column(name = "id_payment_method")
    private Long idPaymentMethod;
    @Column(name = "payment_value")
    private BigDecimal paymentValue;
    @Column(name = "id_contract")
    private Long idContract;

    @ManyToOne
    @JoinColumn(name = "id_contract", insertable = false, updatable = false)
    private Contract contract;
    @ManyToOne
    @JoinColumn(name = "id_payment_method", insertable = false, updatable = false)
    private PaymentMethod paymentMethod;
}
