package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.UserHistoryDTO;
import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.entities.Payment;
import com.eis.apiusers.entities.UserEntity;
import com.eis.apiusers.repositories.ContractRepository;
import com.eis.apiusers.repositories.PaymentRepository;
import com.eis.apiusers.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserHistoryService {
    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ContractRepository contractRepository;

    public List<UserHistoryDTO> getUserHistory(Long idUser) {
        List<UserHistoryDTO> userHistory = new ArrayList<>();

        // Obtener el usuario por ID
        Optional<UserEntity> userOptional = userRepository.findById(idUser);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Obtener los contratos ordenados por "paid"
            List<Contract> contracts = contractRepository.findByIdUserOrderByPaidDesc(idUser);

            for (Contract contract : contracts) {
                UserHistoryDTO historyDTO = new UserHistoryDTO();
                historyDTO.setEmail(user.getEmail());
                historyDTO.setName(user.getName());
                historyDTO.setLastname(user.getLastname());
                historyDTO.setState(user.getState().getNameState());
                historyDTO.setContractId(contract.getIdContract());
                historyDTO.setContractType(contract.getType());

                // Obtener el pago asociado al contrato
                Optional<Payment> paymentOptional = paymentRepository.findByIdContract(contract.getIdContract());
                if (paymentOptional.isPresent()) {
                    Payment payment = paymentOptional.get();
                    historyDTO.setPaymentId(payment.getIdPayment());
                    historyDTO.setPaymentDate(payment.getPaymentDate());
                    historyDTO.setPaymentMethod(payment.getPaymentMethod().getNameMethod());
                    historyDTO.setPaymentValue(payment.getPaymentValue());
                }

                userHistory.add(historyDTO);
            }
        }

        return userHistory;
    }
}