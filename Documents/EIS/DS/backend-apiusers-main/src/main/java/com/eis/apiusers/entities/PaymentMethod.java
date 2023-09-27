package com.eis.apiusers.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "paymentMethods")
@Getter @Setter
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_method", nullable = false, unique = true)
    private Long idPaymentmethods;
    @Column(name = "name_method")
    private String nameMethod;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> payments;
}
