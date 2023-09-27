package com.eis.apiusers.services.impl;

import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class updateNSServiceImpl {
    @Autowired
    private ContractRepository contractRepository;

    public void updateNotificationSetting(Long idContract, Integer notificationSetting) {
        Contract contract = contractRepository.findById(idContract)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con ID: " + idContract));

        contract.setNotificationSetting(notificationSetting);
        contractRepository.save(contract);
    }
}
