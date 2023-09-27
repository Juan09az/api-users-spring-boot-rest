package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.updateContractAndRegisterPayment;
import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.repositories.ContractRepository;
import com.eis.apiusers.dto.RestartContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestartContractService {
    @Autowired
    private ContractRepository contractRepository;

    public void restartContract(Long idContract, updateContractAndRegisterPayment restartContract) {
        Contract contract = contractRepository.findById(idContract)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con ID: " + idContract));

        // Actualizar los campos del contrato
        contract.setValue(restartContract.getValue());
        contract.setDueDate(restartContract.getDueDate());
        contract.setNotificationSetting(restartContract.getNotificationSetting());
        contract.setDescription(restartContract.getDescription());

        contractRepository.save(contract);
    }
}