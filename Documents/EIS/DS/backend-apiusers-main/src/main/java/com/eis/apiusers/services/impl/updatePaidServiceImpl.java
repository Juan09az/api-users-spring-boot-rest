package com.eis.apiusers.services.impl;

import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class updatePaidServiceImpl {
    @Autowired
    private ContractRepository contractRepository;

    public void updatePaidStatus(Long idContract, boolean paid) {
        Contract contract = contractRepository.findById(idContract)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con ID: " + idContract));

        contract.setPaid(paid);
        contractRepository.save(contract);
    }
}
