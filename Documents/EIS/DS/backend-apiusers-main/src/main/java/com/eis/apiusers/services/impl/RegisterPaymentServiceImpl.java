package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.RegisterPaymentDto;
import com.eis.apiusers.dto.updateContractAndRegisterPayment;
import com.eis.apiusers.entities.Payment;
import com.eis.apiusers.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterPaymentServiceImpl {
    private final PaymentRepository paymentRepository;

    @Autowired
    public RegisterPaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void registerPayment(updateContractAndRegisterPayment registerPaymentDto) {
        Payment payment = new Payment();
        payment.setIdContract(registerPaymentDto.getIdContract());
        payment.setIdPaymentMethod(registerPaymentDto.getIdPaymentMethod());
        payment.setPaymentDate(registerPaymentDto.getPaymentDate());
        payment.setPaymentValue(registerPaymentDto.getPaymentValue());
        payment.setAnnotations(registerPaymentDto.getAnnotations());

        paymentRepository.save(payment);
    }
}
