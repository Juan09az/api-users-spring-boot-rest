package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.CreateContractPersonalizedDto;
import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.entities.ServiceUser;
import com.eis.apiusers.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateContractPersonalizedServiceImpl {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CreateServiceContractServiceImpl createServiceContractService;
/*
    @Autowired
    public CreateContractPersonalizedServiceImpl(ContractRepository contractRepository, CreateServiceContractServiceImpl createServiceContractService) {
        this.contractRepository = contractRepository;
        this.createServiceContractService = createServiceContractService;
    }*/

    public Contract createContract(CreateContractPersonalizedDto dto) {
        // Invocar servicio para crear el servicio asociado al contrato
        ServiceUser serviceUser = createServiceContractService.createServiceContract(dto.getIdService(), dto.getFrequency(), dto.getNameService());

        // Crear contrato
        Contract contract = new Contract();
        contract.setIdUser(dto.getIdUser());
        contract.setType(dto.getType());
        contract.setDescription(dto.getDescription());
        contract.setIdService(serviceUser.getIdService());
        contract.setValue(dto.getValue());
        contract.setDueDate(dto.getDueDate());
        contract.setNotificationSetting(dto.getNotificationSetting());

        // Guardar el contrato en la base de datos
        return contractRepository.save(contract);
    }
}