package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.PaymentMethodDto;
import com.eis.apiusers.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<PaymentMethodDto> getAllPaymentMethods() {
        return paymentMethodRepository.findAll().stream()
                .map(paymentMethod -> {
                    PaymentMethodDto dto = new PaymentMethodDto();
                    dto.setIdPaymentmethods(paymentMethod.getIdPaymentmethods());
                    dto.setNameMethod(paymentMethod.getNameMethod());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
