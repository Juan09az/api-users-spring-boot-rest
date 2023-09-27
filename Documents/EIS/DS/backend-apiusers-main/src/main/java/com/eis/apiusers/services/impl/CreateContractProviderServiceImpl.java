package com.eis.apiusers.services;

import com.eis.apiusers.dto.CreateContractProviderDto;
import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateContractProviderServiceImpl {

    private final ContractRepository contractRepository;

    @Autowired
    public CreateContractProviderServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract createContract(CreateContractProviderDto contractDto) {
        Contract contract = new Contract();
        contract.setIdUser(contractDto.getIdUser());
        contract.setType(contractDto.getType());
        contract.setDescription(contractDto.getDescription());
        contract.setIdProvider(contractDto.getIdProvider());
        contract.setSubscriptionCode(contractDto.getSubscriptionCode());
        return contractRepository.save(contract);
    }
}
