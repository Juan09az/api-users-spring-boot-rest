package com.eis.apiusers.controllers;

import com.eis.apiusers.dto.PaymentMethodDto;
import com.eis.apiusers.entities.PaymentMethod;
import com.eis.apiusers.services.impl.PaymentMethodService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/paymentmethods")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping(value = "/list")
    @ApiOperation("returns all payment methods registered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Information not found")
    })
    public ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethods() {
        List<PaymentMethodDto> paymentMethods = paymentMethodService.getAllPaymentMethods();
        return ResponseEntity.ok(paymentMethods);
    }
}
